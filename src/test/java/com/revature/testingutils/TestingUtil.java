package com.revature.testingutils;

import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

/**
 * Class used to reduce redundancy across all testing packages
 *
 */
public class TestingUtil {

	private TestingUtil() {}
	
	private static final Validator ACCESSOR_VALIDATOR = ValidatorBuilder.create()
            .with(new GetterTester())
            .with(new SetterTester())
            .build();
	
	public static void validate(final Class<?> c) {
		ACCESSOR_VALIDATOR.validate(PojoClassFactory.getPojoClass(c));
	}
}
