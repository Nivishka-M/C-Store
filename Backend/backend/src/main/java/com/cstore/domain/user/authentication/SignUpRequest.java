package com.cstore.domain.user.authentication;

import com.cstore.dto.UserAddressDto;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private List<UserAddressDto> addresses;

    private List<Integer> telephoneNumbers;
}
