package com.spring.test.Controllers;


import com.spring.test.Data.Client;
import com.spring.test.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ClientController {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @GetMapping("/")
    public String getMain(Model model){
        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients",clients);
        return "main";
    }

    @PostMapping("/addClient")
    public String addClient(@Valid @ModelAttribute(name = "client") Client client, Model model) {
        clientRepository.save(client);
        model.addAttribute("clients", clientRepository.findAll());
        return "redirect:/";
    }

    @GetMapping("/getAddClient")
    public String showAddClientForm(@ModelAttribute(name = "client") Client client){
        return "AddClient";
    }

    @GetMapping("/edit/{id}")
    public String showEditClientForm(@PathVariable long id, Model model){
        Client client_buf = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Invalid id: " + id));
        model.addAttribute("client",client_buf);
        return "EditClient";
    }

    @PostMapping("/update/{id}")
    public String updateClient(@PathVariable long id, @Valid Client client, BindingResult result,Model model){
        if (result.hasErrors()){
            return "EditClient";
        }
        clientRepository.save(client);
        model.addAttribute("clients",clientRepository.findAll());
        return "redirect:/";
    }


    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable long id, Model model){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Invalid id - " + id));
        clientRepository.delete(client);
        model.addAttribute("clients",clientRepository.findAll());
        return "redirect:/";
    }

}
