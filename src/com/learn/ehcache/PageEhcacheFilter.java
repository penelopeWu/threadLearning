package com.learn.ehcache;

import net.sf.ehcache.constructs.blocking.LockTimeoutException;
import net.sf.ehcache.constructs.web.AlreadyCommittedException;
import net.sf.ehcache.constructs.web.AlreadyGzippedException;
import net.sf.ehcache.constructs.web.filter.FilterNonReentrantException;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * 一般情况下SimplePageCachingFilter就够用了，
 * 这里是为了满足当前系统需求才做了覆盖操作。
 * 使用SimplePageCachingFilter需要在web.xml中配置cacheName，
 * cacheName默认是SimplePageCachingFilter，对应ehcache.xml中的cache配置。
 */
public class PageEhcacheFilter extends SimplePageCachingFilter {

    private final static Logger logger = Logger.getLogger(PageEhcacheFilter.class);

    private final static String FILTER_URL_PATTERNS = "patterns";

    private static String[] cacheURLs;

    private void init() {
        String patterns = filterConfig.getInitParameter(FILTER_URL_PATTERNS);
        cacheURLs = StringUtils.split(patterns, ",");
    }

    @Override
    protected void doFilter(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws AlreadyGzippedException, AlreadyCommittedException, FilterNonReentrantException, LockTimeoutException, Exception {
        if (cacheURLs == null) {
            init();
        }
        String url = request.getRequestURI();
        boolean flag = false;
        if (cacheURLs != null && cacheURLs.length > 0) {
            for (String cacheURL : cacheURLs) {
                if (url.contains(cacheURL.trim())) {
                    flag = true;
                    break;
                }
            }
        }
        if(flag){
            String query = request.getQueryString();
            if(query != null){
                query = "?" + query;
            }
            logger.info("当前请求被缓存：" + url + query);
            super.doFilter(request,response,chain);
        }else {
            chain.doFilter(request,response);
        }

    }

    private boolean headerContains(final HttpServletRequest request,final String header,final String value){
        logRequestHeaders(request);
        final Enumeration accepted = request.getHeaders(header);
        while(accepted.hasMoreElements()){
            final String headerValue = (String) accepted.nextElement();
            if (headerValue.indexOf(value) != -1){
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean acceptsGzipEncoding(HttpServletRequest request) {
        boolean ie6 = headerContains(request,"User-Agent","MSIE 6.0");
        boolean ie7 = headerContains(request,"User-Agent","MSIE 7.0");
        return acceptsEncoding(request,"gzip") || ie6 || ie7;
    }
}
