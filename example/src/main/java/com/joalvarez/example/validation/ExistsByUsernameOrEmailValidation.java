package com.joalvarez.example.validation;

import com.joalvarez.example.data.dao.UserDAO;
import com.joalvarez.example.data.model.User;
import com.joalvarez.example.data.repository.UserRepository;
import com.joalvarez.example.validation.general.GenericValidation;
import com.joalvarez.example.validation.interfaces.ExistsByUsernameOrEmail;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class ExistsByUsernameOrEmailValidation extends GenericValidation<ExistsByUsernameOrEmail, String, UserRepository, User, Long, UserDAO> {

	public ExistsByUsernameOrEmailValidation(UserDAO dao) {
		super(dao);
	}

	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
		return !this.dao.existsUsernameOrEmail(s, s);
	}
}
