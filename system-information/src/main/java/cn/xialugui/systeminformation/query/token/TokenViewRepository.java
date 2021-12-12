package cn.xialugui.systeminformation.query.token;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenViewRepository extends JpaRepository<TokenView, Long> {
    TokenView findByToken(String token);
}
