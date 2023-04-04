//package com.example.money.filter;
//
//
//import com.example.money.pojo.User;
//import com.example.money.service.Impl.userServiceImpl;
//import com.example.money.utils.JWTUtil;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.AntPathMatcher;
//
//
//import java.io.IOException;
//
///**
// * @author lzx
// * @date 2022-11-12 16:21
// * @description
// */
//@Controller
//@WebFilter(filterName = "loginCheck" ,urlPatterns = "/*")
//public class LoginCheckFilter implements Filter {
//    public static final AntPathMatcher PATH_MATCHER=new AntPathMatcher();
//    @Value("${Bath.secret}")
//    private String secret;
//    @Autowired
//    private userServiceImpl userService;
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request=(HttpServletRequest) servletRequest;
//        HttpServletResponse response=(HttpServletResponse) servletResponse;
//        String token = request.getHeader("Authorization");
//        String requestURI = request.getRequestURI();
//        //允许访问的路径
//        String[] urls=new String[]{
//                "/user/login",
//                "/user/picUpload"
////                "/funds/**"
//        };
//        if (token!=null && token!=""){
//            String s =JWTUtil.verifyToken(token, secret);
//            String openid = s.replaceAll("\\\"", "");
//            User user = userService.getUser(openid);
//            request.getSession().setAttribute("openid",openid);
//            if (user!=null){
//                filterChain.doFilter(request,response);
//            }
//        }
//        //对请求的路径做匹配，若符合允许访问的路径，则放行
//        boolean check = check(urls, requestURI);
//        if (check){
//            filterChain.doFilter(request,response);
//            return ;
//        }
//
//
//    }
//
//    /**
//     * 对请求路径做判断，是否符合放行要求
//     * @param urls
//     * @param requestURI
//     * @return
//     */
//    public boolean check(String[] urls,String requestURI){
//        for (String url:urls) {
//            boolean match = PATH_MATCHER.match(url, requestURI);
//            if (match){
//                return true;
//            }
//        }
//        return false;
//    }
//}
//
