package org.verlet.site.action;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.verlet.config.annotation.WebController;
import org.verlet.site.service.AccountService;

@WebController
public class AccountController {

	@Inject 
	AccountService accountService;

	@RequestMapping(value = "account/list", method = RequestMethod.GET)
	public String list(Map<String, Object> model) {
		model.put("account", this.accountService.getAllAccounts());
		return "account/list";
	}
}
