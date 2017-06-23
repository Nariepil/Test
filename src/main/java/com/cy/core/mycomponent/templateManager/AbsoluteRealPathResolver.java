package com.cy.core.mycomponent.templateManager;

import java.io.File;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.cy.util.RealPathResolver;

/**
 * @author dongao
 *
 */
@Component
public class AbsoluteRealPathResolver implements RealPathResolver{
	@Override
	public String get(String path) {
		Assert.hasLength(path,"path lenght must > 0");
		return path.replace("/", File.separator);
	}
}
