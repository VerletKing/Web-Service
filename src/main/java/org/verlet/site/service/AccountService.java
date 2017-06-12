package org.verlet.site.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.verlet.site.bean.Account;

//@Validated只可以验证类或者接口上，可以在方法参数上使用
//@ValidateOnExecution可以在接口上（应用在它所有的方法上）和每个方法上标注，在方法参数上不错使用
@Validated  
public interface AccountService {

	@NotNull
	public List<Account> getAllAccounts();
	public Account getAccount(long id);
	public Account saveAccount(
			@NotNull(message = "{validate.accountService.saveAccount.account}")
			@Valid   //级联验证，验证自己的字段
			Account account);
	public void deleteAccount(long id);
}
