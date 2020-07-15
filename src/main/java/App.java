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

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("animals", Animal.type);
            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());


//        get("/", (req, res)->{
//            Map<String, Object> model = new HashMap<>();
//            model.put("allEndangered", EndangeredAnimal.all());
//            model.put("allAnimals", Animal.all());
//            model.put("allSightings", Sighting.all());
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());

//        get("/dashboard", (req, res)-> {
//            Map<String, Object> model = new HashMap<>();
//            model.put("allAnimals", Animal.all());
//            model.put("allEndangered", EndangeredAnimal.all());
//            model.put("allSightings", Sighting.all());
//            return new ModelAndView(model, "dashboard.hbs");
//        }, new HandlebarsTemplateEngine());

//        get("/new-animal", (req, res)->{
//            Map<String, Object> model = new HashMap<>();
//            return new ModelAndView(model, "new-animal.hbs");
//        },  new HandlebarsTemplateEngine());

//        post("/new-animal", (req, res)->{
//            Map<String, Object> model = new HashMap<>();
//            int age = Integer.parseInt(req.queryParams("age"));
//            String name = req.queryParams("name");
//            String health = req.queryParams("health");
//            Animal newAnimal = new Animal(name, age, health);
//            newAnimal.save();
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());

        post("/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));
            String health = request.queryParams("health");
            Animal animal = new Animal(name, age, health);
            animal.save();
            model.put("animals", Animal.all());
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("animals", Animal.all());
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Animal animal = Animal.find(Integer.parseInt(request.params(":id")));
            model.put("animal", animal);
            model.put("animals", Animal.all());
            return new ModelAndView(model, "edit-animal.hbs");
        }, new HandlebarsTemplateEngine());

//        post("/animals/:id/edit", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            Animal animal = Animal.find(Integer.parseInt(request.params(":id")));
//            String name = request.queryParams("name");
//            animal.update(name);
//            model.put("animals", Animal.all());
//            return new ModelAndView(model, "animals.hbs");
//        }, new HandlebarsTemplateEngine());

//        post("/new-sighting", (req, res)->{
//            Map<String, Object> model = new HashMap<>();
//            int id = Integer.parseInt(req.queryParams("selectedAnimal"));
//            String location = req.queryParams("location");
//            String ranger = req.queryParams("ranger");
//            Sighting newSighting = new Sighting(id, location, ranger);
//            newSighting.save();
//            model.put("newSighting", newSighting.find(newSighting.getId()));
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        post("/new-endangered", (req, res)->{
//            Map<String, Object> model = new HashMap<>();
//            int id = Integer.parseInt(req.queryParams(""));
//            String name = req.queryParams("name");
//            String status = req.queryParams("status");
//            String old = req.queryParams("old");
//            EndangeredAnimal newEndangered = new EndangeredAnimal(1, name, status, old);
//            model.put("newEndangered", newEndangered);
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());

        get("sightings/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("animals", Animal.all());
            model.put("endangereds", EndangeredAnimal.all());
            return new ModelAndView(model, "sighting-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int animalId = Integer.parseInt(request.queryParams("animalId"));
            String location = request.queryParams("location");
            String ranger = request.queryParams("ranger");
            Sighting sighting = new Sighting(animalId, location, ranger);
            sighting.save();
            model.put("sightings", Sighting.all());
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sightings", Sighting.all());
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightings/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Sighting sighting = Sighting.find(Integer.parseInt(request.params(":id")));
            sighting.delete();
            model.put("sightings", Sighting.all());
            return new ModelAndView(model,"sightings.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Sighting sighting = Sighting.find(Integer.parseInt(request.params(":id")));
            model.put("sighting", sighting);
            model.put("sightings", Sighting.all());
            model.put("template", "templates/edit-sighting.hbs");
            return new ModelAndView(model, "edit-sighting.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
