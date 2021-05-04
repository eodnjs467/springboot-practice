package org.zerock.club.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.zerock.club.entity.ClubMember;
import org.zerock.club.entity.ClubMemberRole;
import org.zerock.club.repository.ClubMemberRepository;
import org.zerock.club.security.dto.ClubAuthMemberDto;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubOAuth2UserDetailsService extends DefaultOAuth2UserService {

    private final ClubMemberRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("----------------------------");
        log.info("userRequest : " + userRequest);   // spring security request 객체

        String clientName = userRequest.getClientRegistration().getClientName();

        log.info("clientName : " + clientName); //google    출력
        log.info(userRequest.getAdditionalParameters());

        OAuth2User oAuth2User = super.loadUser(userRequest);

        log.info("===============================");
        oAuth2User.getAttributes().forEach((k, v) -> {
            log.info(k + ":" + v);      //sub, picture, email, email_verified, 등 출력
        });

        String email = null;

        if (clientName.equals("Google")) {
            email = oAuth2User.getAttribute("email");
        }

        log.info("EMAIL : " + email);

//        ClubMember clubMember = saveSocialMember(email);
//
//        return oAuth2User;

        ClubMember member = saveSocialMember(email);

        ClubAuthMemberDto clubAuthMemberDto = new ClubAuthMemberDto(
                member.getEmail(),
                member.getPassword(),
                true,
                member.getRoleSet().stream().map(
                        role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toList()),
                oAuth2User.getAttributes()
        );
        clubAuthMemberDto.setName(member.getName());

        return clubAuthMemberDto;
    }

    private ClubMember saveSocialMember(String email) {
        Optional<ClubMember> result = repository.findByEmail(email, true);

        if (result.isPresent()) {
            return result.get();
        }

        ClubMember clubMember = ClubMember.builder().email(email)
                .name(email)
                .password(passwordEncoder.encode("1111"))
                .fromSocial(true)
                .build();
        clubMember.addMemberRole(ClubMemberRole.USER);

        repository.save(clubMember);

        return clubMember;
    }

}
