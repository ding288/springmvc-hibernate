package com.di.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.di.entity.Person;
import com.di.service.PersonService;

@Controller
@RequestMapping(path = "/person")
public class PersonAction {
	@Autowired
	private PersonService personService;

	@RequestMapping(path = "/list")
	public String list(Model model) {
		model.addAttribute("persons", personService.findAll());
		return "/person/list";
	}

	@RequestMapping(path = "/add")
	public String add(Model model) {
		return "/person/edit";
	}

	@RequestMapping(path = "/edit")
	public String edit(Integer id, Model model) {
		model.addAttribute("person", personService.get(id));
		return "/person/edit";
	}

	@RequestMapping(path = "/save")
	public String save(Person person, Model model) {
		if (person.getId() == null) {
			personService.create(person);
		} else {
			personService.update(person);
		}
		return "redirect:/person/list.htm";
	}
}
