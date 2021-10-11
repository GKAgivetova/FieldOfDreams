package com.gulnazagivetova.field_of_dreams.field_of_dreams.service;

import com.gulnazagivetova.field_of_dreams.field_of_dreams.dao.HistoryRepository;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.dao.UserRepository;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.dao.WordRepository;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.entity.Char;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.entity.History;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.entity.User;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.entity.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Random;

@Service
public class WordService {

    private int rnd;

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    boolean bGuessedCorrectly;

    private String choicelist;

    private boolean bResult;

    private String dispstr;

    private Word word;

    private String answer2;

    private boolean startPage;

    @Autowired
    private HistoryRepository historyRepository;

    public void save(Word word) { wordRepository.save(word); }

    public List<Word> listAll() {
        return wordRepository.findAll();
    }

    public Word get(Long id) {
        return wordRepository.findById(id).get();
    }

    public void saveChars(Char c) {
        startPage = false;
        System.out.println(c.getChars1());
        choicelist += c.getChars1();
        System.out.println(choicelist);
//        if(choicelist.length() > 10)
//            choicelist = choicelist.substring(0,9);

//        System.out.println(choicelist);
        bResult = false;
        dispstr = "";
        String secretWord = word.getAnswer();
        System.out.println(secretWord);
        int numChars = secretWord.length();
//
        String choice = c.getChars1();
//
        for (int i = 0; i < numChars; i++)
        {
            bGuessedCorrectly = true;
            boolean bCorrect = false;
            for(int j = 0; j < choicelist.length(); j++)
            {
                if(choice.indexOf(secretWord.charAt(i)) >= 0)
                    bResult = true;
                if (secretWord.charAt(i) == choicelist.charAt(j))
                {
                    dispstr += " " + secretWord.charAt(i) + " ";
                    bCorrect = true;
                    break;
                }
            }
            if(bCorrect == false)
            {
                dispstr += " _ ";
                bGuessedCorrectly = false;
            }
        }
        if(choicelist.length() >= 10)
        {
            System.out.println("You have reached maximum amount of guesses.");
            bGuessedCorrectly = false;
        }
        System.out.println(dispstr);
    }

    public String getDispstr() {
        return dispstr;
    }

    public void setDispstr(String dispstr) {
        this.dispstr = dispstr;
    }

    public boolean isbGuessedCorrectly() {
        return bGuessedCorrectly;
    }

    public void setbGuessedCorrectly(boolean bGuessedCorrectly) {
        this.bGuessedCorrectly = bGuessedCorrectly;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public boolean isStartPage() {
        return startPage;
    }

    //    public Word getRndWord(Long userId) {
//        List<Word> listWord = wordRepository.findAll();
//
//        int[] ints = new int[listWord.size()];
//        int i = 0;
//        for (Word word1 : listWord) {
//            System.out.println(word1.getId());
//            ints[i] = Math.toIntExact(word1.getId());
//            i++;
//        }
//
//        int rnd = new Random().nextInt(ints.length);
//
//        c.setRnd(rnd);
//    }


    public int getRnd() {
        return rnd;
    }

    public int[] listWord() {
        List<Word> listWord = wordRepository.findAll();

        int[] ints = new int[listWord.size()];
        int i = 0;
        for (Word word1 : listWord) {
            System.out.println(word1.getId());
            ints[i] = Math.toIntExact(word1.getId());
            i++;
        }
        System.out.println(rnd);
        return ints;
    }

    public void rndWord(Principal principal) {
        List<Word> listWord = wordRepository.findAll();

        int[] ints = new int[listWord.size()];
        int i = 0;
        for (Word word1 : listWord) {
            System.out.println(word1.getId());
            ints[i] = Math.toIntExact(word1.getId());
            i++;
        }
        User user = userRepository.findByEmail(principal.getName());
        List<History> list = user.getHistoryList();

        for (History h : list) {
            System.out.println(h.getQuestion());
        }

        choicelist = "";
        startPage = true;

        rnd = new Random().nextInt(ints.length);
    }

    public String[] getInts2(Word word, Char c) {
        int numChars = word.getAnswer().length();
        String[] ints2 = new String[numChars];

        for(int j = 0; j < numChars; j++) {
            ints2[j] = "_";
        }

        this.word = word;
//        System.out.println(c.getChars1());

//        choicelist += c.getChars1();
//        System.out.println(choicelist);
        return ints2;
    }

//    public void startPage() {
//        dispstr = "";
//        word = wordRepository.findById((long) this.rnd).get();
//        for (int i = 0; i < word.getAnswer().length(); i++)
//            dispstr += " _ ";
//    }
}
