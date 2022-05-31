package com.mortadha.vetements.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mortadha.vetements.entities.Marque;
import com.mortadha.vetements.entities.Vetement;
import com.mortadha.vetements.service.MarService;
import com.mortadha.vetements.service.VetementService;
@Controller
public class VetementController {
@Autowired
VetementService vetementService;
@Autowired
MarService marService;
@RequestMapping("/showCreate")
public String showCreate(ModelMap modelMap)
{
List<Marque> mars = marService.findAll();
Vetement prod = new Vetement();
Marque mar = new Marque();
mar = mars.get(0); // prendre la première marégorie de la liste
prod.setMarque(mar); //affedter une marégorie par défaut au vetement pour éviter le pb avec une marégorie null
modelMap.addAttribute("marques", mars);
modelMap.addAttribute("vetement", new Vetement());
modelMap.addAttribute("mode", "new");
return "formVetement";
}
@RequestMapping("/saveVetement")
public String saveVetement(@Valid Vetement vetement,
BindingResult bindingResult)
{
System.out.println(vetement);
if (bindingResult.hasErrors()) return "formVetement";
vetementService.saveVetement(vetement);

return "redirect:/ListeVetements";
}

@RequestMapping("/showCreateMar")
public String showCreateMar(ModelMap modelMap)
{
modelMap.addAttribute("marques", new Marque());
modelMap.addAttribute("mode", "new");
return "formMarque";
}
@RequestMapping("/saveMarque")
public String saveMarque(@ModelAttribute("marque") Marque marque,ModelMap modelMap) throws ParseException 
{
Marque saveMarque = marService.saveMarque(marque);
return "redirect:/ListeMar";
}

@RequestMapping("/ListeMar")
public String listeVetements(ModelMap modelMap)
{
List <Marque> mars = marService.findAll();
modelMap.addAttribute("marques", mars);
return "ListeMar";
}

@RequestMapping("/ListeVetements")
public String listeVetements(ModelMap modelMap,
@RequestParam (name="page",defaultValue = "0") int page,
@RequestParam (name="size", defaultValue = "3") int size)
{
Page<Vetement> prods = vetementService.getAllVetementsParPage(page, size);
modelMap.addAttribute("vetements", prods);
 modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
modelMap.addAttribute("currentPage", page);
return "listeVetements";
}


@RequestMapping("/supprimerVetement")
public String supprimerVetement(@RequestParam("id") Long id,
ModelMap modelMap,
@RequestParam (name="page",defaultValue = "0") int page,
@RequestParam (name="size", defaultValue = "3") int size)
{
vetementService.deleteVetementById(id);
Page<Vetement> prods = vetementService.getAllVetementsParPage(page, 
size);
modelMap.addAttribute("vetements", prods);
modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
modelMap.addAttribute("currentPage", page);
modelMap.addAttribute("size", size);
return "listeVetements";
}
@RequestMapping("/supprimerMarque")
public String supprimerMarque(@RequestParam("id") Long id,
 ModelMap modelMap)
{ 
marService.deleteMarqueById(id);
List<Marque> mars = marService.findAll();
modelMap.addAttribute("marques", mars);
return "ListeMar";
}

@RequestMapping("/modifierVetement")
public String editerVetement(@RequestParam("id") Long id,ModelMap modelMap)
{
Vetement p= vetementService.getVetement(id);
List<Marque> mars = marService.findAll();
mars.remove(p.getMarque());
modelMap.addAttribute("marques", mars);
modelMap.addAttribute("vetement", p);
modelMap.addAttribute("mode", "edit");
return "formVetement";
}
@RequestMapping("/updateVetement")
public String updateVetement(@ModelAttribute("vetement") Vetement vetement,
@RequestParam("date") String date,ModelMap modelMap) throws ParseException {
	//conversion de la date 
	 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	 Date dateCreation = dateformat.parse(String.valueOf(date));
	 vetement.setDateCreation(dateCreation);
	 
	 vetementService.updateVetement(vetement);
	 List<Vetement> prods = vetementService.getAllVetements();
	 modelMap.addAttribute("vetements", prods);
	return "listeVetements";
	}
@RequestMapping("/modifierMarque")
public String editerMarque(@RequestParam("id") Long id,ModelMap modelMap)
{
Marque c= marService.getMarque(id);
modelMap.addAttribute("marques", c);
modelMap.addAttribute("mode", "edit");
return "formMarque";
}
@RequestMapping("/updateMarque")
public String updateMarque(@ModelAttribute("marque") Marque marque,ModelMap modelMap) throws ParseException {
	marService.updateMarque(marque);
	 List<Marque> mars = marService.findAll();
	 modelMap.addAttribute("marques", mars);
	return "ListeMar";
	}

@RequestMapping("/searchVetement")
public String searchPersonne(ModelMap modelMap,@Valid String nomMar) {
	List<Vetement> prods = vetementService.findByNomMarque(nomMar);

	modelMap.addAttribute("vetements", prods);

	return "/searchVetement";
}




}