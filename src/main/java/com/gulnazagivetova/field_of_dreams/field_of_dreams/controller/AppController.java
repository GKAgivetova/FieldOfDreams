package com.gulnazagivetova.field_of_dreams.field_of_dreams.controller;

import com.gulnazagivetova.field_of_dreams.field_of_dreams.entity.Char;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.entity.Role;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.entity.User;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.entity.Word;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.service.UserService;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class AppController {

    @Autowired
    private UserService userService;

    @Autowired
    private WordService wordService;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user) {
        userService.saveUserWithDefaultRole(user);

        return "register_success";
    }

    @GetMapping("/list_users")
    public String viewUsersList(Model model) {
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUser", listUsers);

        return "users";
    }

    @GetMapping("/manager_info")
    public String viewAdminList(Model model) {
        List<Word> listWord = wordService.listAll();
        model.addAttribute("listWord", listWord);

        return "users_2";
    }

    @GetMapping("/view_field_of_dreams")
    public String viewFieldOfDreams(Model model, Principal principal) {
        Char c = new Char();
        c.setId(userService.getId(principal.getName()));
//        List<Word> listWord = wordService.listAll();

        int[] ints = wordService.listWord();
//        int i = 0;
//        for (Word word1 : listWord) {
//            System.out.println(word1.getId());
//            ints[i] = Math.toIntExact(word1.getId());
//            i++;
//        }

//        int rnd = new Random().nextInt(ints.length);
//
//        c.setRnd(rnd);

        System.out.println(ints[0]);
        System.out.println(c.getId());

        Word word = wordService.get((long) ints[wordService.getRnd()]);
//        System.out.println(wordService.isStartPage());
        String answer = "";
        if (wordService.isStartPage() == true) {
            for (int i = 0; i < word.getAnswer().length(); i++) {
                answer += " _ ";
                wordService.setDispstr(answer);
                wordService.setbGuessedCorrectly(false);
            }
        }
        System.out.println(answer);
        answer = wordService.getDispstr();
        String question = word.getQuestion();

//        int numChars = word.getAnswer().length();
        String[] ints2 = wordService.getInts2(word, c);

//        for(int j = 0; j < numChars; j++) {
//            ints2[j] = "_";
//        }

//        System.out.println(c.getChars1());
        if (wordService.isbGuessedCorrectly() == false) {
            c.setAnswer("Keep Playing");
        } else {
            c.setAnswer("Congratulation you won the game");
//            wordService.setAnswer2("Congratulation you won the game");
//            System.out.println(wordService.isbGuessedCorrectly());
//            return "start_page";
        }
//        boolean bGuessedCorrectly = true;
//        String dispstr = "";
//        String choice = c.getChars1();

//        if (c.getChars2() == null) {
//            c.setChars2("");
//            System.out.println(c.getChars2() + " " + c.getChars1());
//        }





        System.out.println(principal.getName());
        model.addAttribute("question", question);
//        model.addAttribute("answer", Arrays.toString(ints2));
        model.addAttribute("answer", answer);
        model.addAttribute("answer2", c.getAnswer());
        model.addAttribute("chars", c);

        return "view_field_of_dreams";
    }

    @GetMapping("/start_page")
    public String viewStartPage(Model model) {
//        wordService.startPage();
//        boolean a = false;
//        String s = "Congratulation you won the game";
//        if (wordService.isbGuessedCorrectly() == true) {
//            a = true;
//        }

//        System.out.println(String.valueOf(wordService.isbGuessedCorrectly()));

        model.addAttribute("answer2", wordService.getAnswer2());
//        model.addAttribute("answer2", s);


        return "start_page";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = userService.get(id);
        List<Role> listRoles = userService.getRoles();

        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);

        return "user_form";
    }

    @GetMapping("/words/edit/{id}")
    public String editWord(@PathVariable("id") Long id, Model model) {
        Word word = wordService.get(id);

        model.addAttribute("word", word);

        return "word_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user) {
        userService.save(user);

        return "redirect:/manager_info";
    }

    @PostMapping("/words/save")
    public String saveWord(Word word) {
        wordService.save(word);

        return "redirect:/manager_info";
    }

    @PostMapping("/index")
    public String saveChars(Char c) {
        wordService.saveChars(c);

        return "redirect:/view_field_of_dreams";
    }

    @GetMapping("/rnd_word")
    public String rndWord(Principal principal) {
        wordService.rndWord(principal);
//        wordService.startPage();

        return "redirect:/view_field_of_dreams";
    }
}
