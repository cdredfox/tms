package controllers;

import play.*;
import play.db.jpa.JPABase;
import play.mvc.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.persistence.EntityManager;

import org.apache.commons.io.FileUtils;

import models.*;
@With(Secure.class)
public class Application extends Controller {
    public static void menus(){
    	try {
    		String path=Application.class.getResource("menus.json").getFile();
			String json=FileUtils.readFileToString(new File(path));
			renderJSON(json);
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static void index(){
    	render();
    }
}