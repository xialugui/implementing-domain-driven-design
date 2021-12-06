package cn.xialugui.systeminformation.interfaces.rest;

import cn.xialugui.systeminformation.query.authenticationlog.AuthenticationLogView;
import cn.xialugui.systeminformation.query.authenticationlog.AuthenticationLogViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authentication_logs")
@RequiredArgsConstructor
public class AuthenticationLogViewResource {
    private final AuthenticationLogViewRepository repository;

    @GetMapping
    public Page<AuthenticationLogView> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

}
