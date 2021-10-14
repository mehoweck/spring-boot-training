package pl.effectivedev.articles.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@NoArgsConstructor
//@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestScope
public class CurrentUser {

    @Setter
    @Getter
    private String user;

}
