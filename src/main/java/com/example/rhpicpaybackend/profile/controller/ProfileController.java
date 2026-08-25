package com.example.rhpicpaybackend.profile.controller;

import com.example.rhpicpaybackend.profile.dto.groupValidations.doPatch;
import com.example.rhpicpaybackend.profile.dto.input.FindMyProfileInputDTO;
import com.example.rhpicpaybackend.profile.dto.input.UpdateMyUserInputDTO;
import com.example.rhpicpaybackend.profile.dto.output.FindMyProfileOutputDTO;
import com.example.rhpicpaybackend.profile.dto.request.UpdateMyUserRequestDTO;
import com.example.rhpicpaybackend.profile.service.ProfileService;
import com.example.rhpicpaybackend.shared.helpers.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {
  private final ProfileService profileService;

  @GetMapping()
  public ResponseEntity<FindMyProfileOutputDTO> findMyProfile(
      Authentication authentication
  ){
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

    return new ResponseEntity<>(
        this.profileService.findMyProfile(
            new FindMyProfileInputDTO(user.getId())
        ),
        HttpStatus.OK
    );
  }

  @PatchMapping()
  public ResponseEntity<FindMyProfileOutputDTO> updateMyProfile(
      Authentication authentication,

      @RequestBody
      @Validated(doPatch.class)
      UpdateMyUserRequestDTO input
  ){
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

    return new ResponseEntity<>(
        this.profileService.updatePartial(
            new UpdateMyUserInputDTO(user.getId(), input)
        ),
        HttpStatus.OK
    );
  }
}
