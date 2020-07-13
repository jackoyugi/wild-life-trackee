import models.Animal;
import models.EndangeredAnimal;
import models.Sighting;
import org.sql2o.Connection;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args){
        Connection con;
        staticFileLocation("/public");

        get("/", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            model.put("allEndangered", EndangeredAnimal.all());
            model.put("allAnimals", Animal.all());
            model.put("allSightings", Sighting.all());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/dashboard", (req, res)-> {
            Map<String, Object> model = new HashMap<>();
            model.put("allAnimals", Animal.all());
            model.put("allEndangered", EndangeredAnimal.all());
            model.put("allSightings", Sighting.all());
            return new ModelAndView(model, "dashboard.hbs");
        }, new HandlebarsTemplateEngine());

        get("/new-animal", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "new-animal.hbs");
        },  new HandlebarsTemplateEngine());

        post("/new-animal", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            int age = Integer.parseInt(req.queryParams("age"));
            String name = req.queryParams("name");
            String health = req.queryParams("health");
            Animal newAnimal = new Animal(name, age, health);
            newAnimal.save();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        post("/new-sighting", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.queryParams("selectedAnimal"));
            String location = req.queryParams("location");
            String ranger = req.queryParams("ranger");
            Sighting newSighting = new Sighting(id, location, ranger);
            newSighting.save();
            model.put("newSighting", newSighting.find(newSighting.getId()));
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        post("/new-endangered", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.queryParams(""));
            String name = req.queryParams("name");
            String status = req.queryParams("status");
            String old = req.queryParams("old");
            EndangeredAnimal newEndangered = new EndangeredAnimal(1, name, status, old);
            model.put("newEndangered", newEndangered);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


    }
}
