package pl.effectivedev.articles.api;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// -----REST--> Filtry -> Interceptor -> Servlet --> Controller  ->> Servlet --> Interceptor --> Filtry

@Component
@RequiredArgsConstructor
public class UserInterceptor implements HandlerInterceptor {

    private final CurrentUser currentUser;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String user = request.getHeader("x-user");
        if (user != null) {
            currentUser.setUser(user);
            return true;
        } else {
            response.sendError(400, "Missing x-user header");
            return false;
        }
    }
}
