package at.htlstp.security.presentation.web;

import at.htlstp.security.domain.UserEntity;
import at.htlstp.security.persistence.UserRepository;
import at.htlstp.security.presentation.web.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public record WebController(UserRepository userRepository, PasswordEncoder passwordEncoder,
                            UserDetailsService userDetailsService) {

    @GetMapping({"", "home"})
    public String displayHome() {
        return "home";
    }

    @GetMapping("login")
    public String displayLogin() {
        return "login";
    }

    @GetMapping("greeting")
    public String getGreeting(Principal principal, Model model) {
        var username = principal == null ? "User" : principal.getName();
        model.addAttribute("greeting", "Hello " + username);
        return "greeting";
    }

    @GetMapping("register")
    public String displayRegistration(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping("register")
    public String registerUser(@Valid UserDto userDto, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors())
            return "register";
        var user = new UserEntity(userDto.getUsername(), userDto.getPassword(), "user");
        userRepository.save(user);
        login(userDto, request);
        return "redirect:/greeting";
    }

    private void login(UserDto userDto, HttpServletRequest request) {
        var userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
        var auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        var securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);
        var session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
    }
}
