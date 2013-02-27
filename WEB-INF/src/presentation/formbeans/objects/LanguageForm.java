package presentation.formbeans.objects;

import utils.validator.ValidatorFormAndAction;

/**
 * Nested-pojo-form-bean for a Language. Language is currently disabled.
 * 
 * @author Automatika - JustInMind SL
 */
public class LanguageForm extends ValidatorFormAndAction {
    private String languagecode=null;
    private String language=null;
    
    public LanguageForm getLanguageForm() {
		return this;
	}    

	public String toString() {
		return this.getLanguage();
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguagecode() {
		return languagecode;
	}

	public void setLanguagecode(String languagecode) {
		this.languagecode = languagecode;
	}
    
}