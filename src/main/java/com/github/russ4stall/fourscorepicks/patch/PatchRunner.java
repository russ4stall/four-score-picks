package com.github.russ4stall.fourscorepicks.patch;

import com.github.russ4stall.fourscorepicks.patch.dao.PatchDao;
import com.github.russ4stall.fourscorepicks.patch.dao.PatchDaoImpl;
import com.opensymphony.xwork2.util.finder.ClassFinder;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * Created by russ on 8/14/14.
 */
public class PatchRunner {
    ServletContext servletContext;

    public void runPatches() {
        PatchDao patchDao = new PatchDaoImpl();
        List<String> completedPatches = patchDao.getCompletedPatches();

        Import2014GamesPatch import2014GamesPatch = new Import2014GamesPatch();
        import2014GamesPatch.setServletContext(servletContext);

        if (!completedPatches.contains(Import2014GamesPatch.class.getName())) {
            if (import2014GamesPatch.run()) {
                patchDao.addCompletedPatch(import2014GamesPatch.getClass().getName());
            } else {
                System.out.println("PATCH FAILED");
            }
        }
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
