package personal.delivery.member.service;

import personal.delivery.member.dto.MemberRequestDto;
import personal.delivery.member.dto.MemberResponseDto;

public interface MemberService {

    MemberResponseDto createSeller(MemberRequestDto memberRequestDto);

    MemberResponseDto createCustomer(MemberRequestDto memberRequestDto);

    MemberResponseDto getMemberInformation(Long id);
}
