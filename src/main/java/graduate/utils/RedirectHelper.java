package graduate.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Component
public class RedirectHelper {
	public ModelAndView redirectTo(String url) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url);
        return new ModelAndView(redirectView);
    }
}
