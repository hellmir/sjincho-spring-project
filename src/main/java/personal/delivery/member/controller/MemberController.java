package personal.delivery.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import personal.delivery.member.dto.MemberRequestDto;
import personal.delivery.member.dto.MemberResponseDto;
import personal.delivery.member.service.MemberService;

import java.net.URI;

@RestController
@RequestMapping("members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("sellers")
    public ResponseEntity<MemberResponseDto> signUpSeller(@Valid @RequestBody MemberRequestDto memberRequestDto) {

        MemberResponseDto memberResponseDto = memberService.createSeller(memberRequestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(memberResponseDto.getId())
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(memberResponseDto);

    }

    @PostMapping("customers")
    public ResponseEntity<MemberResponseDto> signUpCustomer(@Valid @RequestBody MemberRequestDto memberRequestDto) {

        MemberResponseDto memberResponseDto = memberService.createCustomer(memberRequestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(memberResponseDto.getId())
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(memberResponseDto);

    }

    @GetMapping("{id}")
    public ResponseEntity<MemberResponseDto> getMemberInformation(@PathVariable Long id) {

        MemberResponseDto memberResponseDto = memberService.getMemberInformation(id);

        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDto);

    }

}
