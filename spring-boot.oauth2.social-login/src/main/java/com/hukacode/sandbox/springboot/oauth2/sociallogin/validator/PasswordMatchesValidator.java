/*
 * Copyright 2021 hukacode
 */
package com.hukacode.sandbox.springboot.oauth2.sociallogin.validator;

import com.hukacode.sandbox.springboot.oauth2.sociallogin.dto.SignUpRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
    implements ConstraintValidator<PasswordMatches, SignUpRequest> {

  @Override
  public boolean isValid(final SignUpRequest user, final ConstraintValidatorContext context) {
    return user.getPassword().equals(user.getMatchingPassword());
  }
}
