package com.example.italify;

import android.util.Log;

import java.util.ArrayList;

public class DataInitializer {
    public static int maxNumOfLessons = 30;
    public static int maxNumOfVideosPerLesson = 10;
    DataInitializer(){}
//    public static ArrayList<String> lessonsTitles (){
//        ArrayList<String> titles = new ArrayList<>();
//        titles.add("Lesson 1 Alphabets");
//        titles.add("Lesson 2 Greeting");
//        titles.add("Lesson 3 Numbers");
//        titles.add("Lesson 4 Foods");
//        titles.add("Lesson 5 Basic Conversations");
//        titles.add("Lesson 6 Shopping");
//        titles.add("Lesson 7 Verbs");
//        titles.add("Lesson 8 Adjectives");
//        titles.add("Lesson 9 Weather");
//        titles.add("Lesson 10 Travel");
//
//        return titles;
//    }
//
//    public static ArrayList<Integer> lessonsIcons (){
//        ArrayList<Integer> icons = new ArrayList<>();
//        icons.add(R.drawable.business_center_24px);
//        icons.add(R.drawable.local_mall);
//        icons.add(R.drawable.local_pizza);
//        icons.add(R.drawable.star);
//        icons.add(R.drawable.ic_launcher_foreground);
//        icons.add(R.drawable.baseline_add_home_24);
//        icons.add(R.drawable.baseline_star_24);
//        icons.add(R.drawable.baseline_arrow_back_24);
//        icons.add(R.drawable.ic_launcher_background);
//        icons.add(R.drawable.ic_launcher_foreground);
//
//
//        return icons;
//    }

    public static ArrayList<Lesson> allLessons () {
        ArrayList<Lesson> allLessons = new ArrayList<>();
        allLessons.add(new Lesson(R.drawable.greeting               , "Lesson 1 Greeting", 0));
        allLessons.add(new Lesson(R.drawable.food                   , "Lesson 2 Foods", 0));
        allLessons.add(new Lesson(R.drawable.numbers                , "Lesson 3 Numbers", 0));
        allLessons.add(new Lesson(R.drawable.color                  , "Lesson 4 Colors", 0));
        allLessons.add(new Lesson(R.drawable.sport                  , "Lesson 5 Sport", 0));
        allLessons.add(new Lesson(R.drawable.calender               , "Lesson 6 Date & Time", 0));
        allLessons.add(new Lesson(R.drawable.phrase                 , "Lesson 7 Phrases", 5));
        allLessons.add(new Lesson(R.drawable.family                 , "Lesson 8 Family", 10));
        allLessons.add(new Lesson(R.drawable.star                   , "Lesson 9 Relatives", 99990));
        allLessons.add(new Lesson(R.drawable.star                   , "Lesson 10 Give Direction", 99990));
        allLessons.add(new Lesson(R.drawable.star                   , "Lesson 11 Presentations", 99990));
        allLessons.add(new Lesson(R.drawable.star                   , "Lesson 12 Polite Conversations", 99990));
        allLessons.add(new Lesson(R.drawable.star                   , "Lesson 13 Grammar", 99990));
        allLessons.add(new Lesson(R.drawable.star                   , "Lesson 14 Politics", 99990));
        allLessons.add(new Lesson(R.drawable.star                   , "Lesson 15 Dating", 99990));
        allLessons.add(new Lesson(R.drawable.star                   , "Lesson 16 Culture", 99990));
        allLessons.add(new Lesson(R.drawable.star                   , "Lesson 17 Wildelife ", 99990));
//        allLessons.add(new Lesson(R.drawable.baseline_add_home_24   , "Lesson 6 Shopping", 0));
//        allLessons.add(new Lesson(R.drawable.baseline_star_24       , "Lesson 7 Verbs", 0));
//        allLessons.add(new Lesson(R.drawable.baseline_arrow_back_24 , "Lesson 8 Adjectives", 1));
//        allLessons.add(new Lesson(R.drawable.ic_launcher_background , "Lesson 9 Weather", 2));
//        allLessons.add(new Lesson(R.drawable.ic_launcher_foreground , "Lesson 10 Travel", 3));


        return allLessons;
    }

    public static ArrayList<Video>[] allVideos () {
        //TODO(2) should we add thumbnail of videos manually? or we can handle it easily by coding? if not, we should create another class for video like what we did for question
        ArrayList<Video>[] allVideos = new ArrayList[maxNumOfLessons];
        ArrayList<Video> lesson = new ArrayList<>();
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/TBmWeRgujKo\" title=\"Stop saying ‘Arrivederci’! \uD83D\uDC4B\uD83D\uDEAB (5 Alternatives)\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t11, "Stop saying ‘Arrivederci’!",1,1));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/qkwQuuSjtKE\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t12, "How to Say 'I LIKE IT' in Italian",1,2));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/P_cJYg-r4Vg\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t13, "How to Answer the Phone LIKE AN Italian",1,3));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/MbHNaycn7eo\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t14, "STOP saying ‘Arrivederci’",1,4));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/2toCATUfHXE\" title=\"STOP saying PER FAVORE in Italy ❌\uD83E\uDD2F\uD83D\uDE27\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t15, "STOP saying PER FAVORE ",1,5));
        allVideos[0] = lesson;

        lesson = new ArrayList<>();
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/-ZgKa9g_1lU\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t21, "8 Italian Adjectives to Describe FOOD",2,1));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/N165dC51KLQ\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t22, "10 Italian Phrases For Eating Out",2,2));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/6saLEuQIBak\" title=\"5 Food-Related Italian Insults &amp; Compliments \uD83C\uDDEE\uD83C\uDDF9 TOP Italian Idioms\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t23, "5 Food-Related Italian Insults & Compliments",2,3));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/gYJ3AOxwRDI\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t24, "How to Say 'I’m hungry' in Italian",2,4));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/9JLx1NCdac0\" title=\"Learn Italian Vocabulary \uD83C\uDDEE\uD83C\uDDF9\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t25, "Learn Food Vocabulary",2,5));
        allVideos[1] = lesson;
        lesson = new ArrayList<>();
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/EqYIFHauTg4\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t31, "Italian numbers",3,1));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/cEhKCGrROaY\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t32, "How to Write NUMBERS in Italian",3,2));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/UWFtWZPTo10\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t33, "How to form ORDINAL Numbers in Italian",3,3));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/k2buqUX0h9A\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t34, "Italian numbers Vol2",3,4));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/JJJrl5F9v0Q\" ></iframe>",R.drawable.t35, "Italian numbers 1-20",3,5));
        allVideos[2] = lesson;

        lesson = new ArrayList<>();
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/HxxC8F7GV8g\" title=\"Learn the colors in Italian \uD83C\uDDEE\uD83C\uDDF9\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t41, "Learn the colors in Italian",4,1));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/EgDITD3D94g\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t42, "Colors in Italian",4,2));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/3hNrBEUpot4\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t43, "I Colori",4,3));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/KMvZB7_yobs\" title=\"I Colori \uD83C\uDDEE\uD83C\uDDF9| How to Say the Colors in Italian| Learn Italian\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t44, "How to Say the Colors in Italian",4,4));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/lRbkgt9FtAo\" title=\"Learn Italian vocabulary! Learn colors! Write your favorite one in the comment!\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t45, "Learn Italian vocabulary! Learn colors!",4,5));
        allVideos[3] = lesson;


        lesson = new ArrayList<>();
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/RI0m-1WJvFs\" title=\"Names of SPORTS in Italian \uD83C\uDFC0⚽⛳\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t51, "Names of SPORTS in Italian",5,1));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/G_OBxmyyIrs\" title=\"Learn Italian Vocabulary sports\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t52, "Learn Italian Vocabulary sports",5,2));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/SBBlXuxvcMA\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t53, "Can you name these sports in Italian?",5,3));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ZF-_2Krt7hA\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t54, "Sport in Italian ",5,4));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/8IBnrF_f3WU\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t55, "Can you name these sports in Italian?",5,5));
        allVideos[4] = lesson;

        lesson = new ArrayList<>();
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/TZruqqzuiyY\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t61, "Days of the week in Italian",6,1));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/TD5jhmxKlis\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t62, "Months in Italian",6,2));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ygEicmw6qjs\" title=\"\uD83C\uDDEE\uD83C\uDDF9 Learn Italian - The Seasons in Italian \uD83C\uDDEE\uD83C\uDDF9\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t63, "The Seasons in Italian",6,3));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/k4oc6O0lMIM\" title=\"Seasons in Italian\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t64, "The Seasons in Italian V2",6,4));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/A1IiAUtwwJ4\" title=\"\uD83C\uDDEE\uD83C\uDDF9\uD83D\uDCC6 The Days of the Week in Italian\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t65, "The Days of the Week in Italian",6,5));
        allVideos[5] = lesson;

        lesson = new ArrayList<>();
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/vCvilsAe8gY\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t71, "Phrases with ‘CHE’ (Polite to Offensive)",7,1));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/7QYIIMS09ew\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t72, "Travel Phrases You SHOULD Know",7,2));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/uJXAGnAWHXQ\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t73, "Essential Italian Travel Phrases",7,3));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/bA-4W7A6NQ0\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t74, "Useful phone-related Italian phrases",7,4));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/t9DIawREpog\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t75, "Basic Italian phrases",7,5));
        allVideos[6] = lesson;

        lesson = new ArrayList<>();
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/59mqfeq2DvQ\" title=\"Practice your Italian \uD83C\uDDEE\uD83C\uDDF9 - Family members\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t81, "Family members name",8,1));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/xYinX-Btkv4\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t82, "Family in Italian",8,2));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Obvf157lBMo\" title=\"Learn Italian Family Vocabulary - NIPOTE what does this Italian word mean?\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t83, "Learn Italian Family Vocabulary - NIPOTE",8,3));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/KCatX-3iTpE\" title=\"Learn Italian \uD83C\uDDEE\uD83C\uDDF9 - Family members\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t84, "Family members",8,4));
        lesson.add(new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/uwjoXR2jTCs\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>",R.drawable.t85, "Family name in italian",8,5));
        allVideos[7] = lesson;

        //        lesson = new ArrayList<>();
//        lesson.add(new Video(R.drawable.baseline_star_24,R.drawable.baseline_star_24, "lesson 2 video 1 title",2,1));
//        lesson.add(new Video(R.drawable.baseline_arrow_back_24,R.drawable.baseline_arrow_back_24, "lesson 2 video 2 title",2,2));
//        allVideos[1] = lesson;
//        lesson = new ArrayList<>();
//        lesson.add(new Video(R.drawable.baseline_add_home_24,R.drawable.baseline_add_home_24, "lesson 3 video 1 title",3,1));
//        lesson.add(new Video(R.drawable.baseline_star_24,R.drawable.baseline_star_24, "lesson 3 video 2 title",3,2));
//        lesson.add(new Video(R.drawable.baseline_arrow_back_24,R.drawable.baseline_arrow_back_24, "lesson 3 video 3 title",3,3));
//        allVideos[2] = lesson;
//        lesson = new ArrayList<>();
//        lesson.add(new Video(R.drawable.baseline_arrow_back_24,R.drawable.baseline_arrow_back_24, "lesson 4 video 1 title",4,1));
//        allVideos[3] = lesson;
//        lesson = new ArrayList<>();
//        lesson.add(new Video(R.drawable.ic_launcher_background,R.drawable.ic_launcher_background, "lesson 5 video 1 title",5,1));
//        lesson.add(new Video(R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground, "lesson 5 video 2 title",5,2));
//        allVideos[4] = lesson;

        return allVideos;
    }

    public static ArrayList<Question>[][] allQuestions (){
        ArrayList<Question> [][] allquestions = new ArrayList[maxNumOfLessons][maxNumOfVideosPerLesson];
        ArrayList<Question> practice = new ArrayList<>();
        practice.add(new Question(0, "What does \"Arrivederci\" mean in Italian?", "Hello", "Goodbye ","Thank you","None of the above",2, "Cosa significa la parola Arrivederci in inglese?"));
        practice.add(new Question(0, "Which of the following is a slang way to say \"I'll catch you later\" in Italian?", "Ciao ", "Ci becchiamo","A più tardi","None of the above",2, "Quale tra i seguenti è un modo gergale per dire \"Ci vediamo dopo\" in italiano?"));
        practice.add(new Question(0, "How would you say \"see you soon\" in Italian?", "Ci vediamo", "Alla prossima","A più tardi","None of the above",1, "Come si dice \"a presto\" in italiano?"));
        allquestions[0][0] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "What is the literal translation of \"Mi piace cucinare\" in English?", "I cook", "Cooking is pleasing to me","I like cooking","None of the above",2, "Qual è la traduzione letterale di \"Mi piace cucinare\" in inglese?"));
        practice.add(new Question(0, "How do you say \"I like to take photos\" in Italian?", "Mi piace fare le foto", "Mi piace cucinare","Mi piace Roma","None of the above",1, "Come si dice \"mi piace fare foto\" in italiano?"));
        practice.add(new Question(0, "Which sentence means \"I like Rome\" in Italian?", "Mi piace parlare italiano", "Mi piace Roma","Mi piace fare le foto","None of the above",2, "Quale frase significa \"Mi piace Roma\" in italiano?"));
        allquestions[0][1] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "What word do Italians use to answer the phone, which means \"ready\"?", "Ciao", "Pronto","Buongiorno","None of the above",2, "Che parola usano gli italiani per rispondere al telefono, che significa \"pronto\"?"));
        practice.add(new Question(0, "What is the meaning of \"Pronto\" when answering the phone in Italy?", "Hello", "Goodbye","Ready","None of the above",3, "Qual è il significato di \"Pronto\" quando si risponde al telefono in Italia?"));
        practice.add(new Question(0, "How do Italians typically end a phone call?", "With \"Buongiorno\"", "With \"Pronto\"","With multiple \"Ciao\"s","None of the above",3, "Come terminano tipicamente le telefonate gli italiani?"));
        allquestions[0][2] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "Which phrase is a casual way to say \"goodbye\" to friends or family in Italian?", "Arrivederci", "A dopo","Stammi bene","None of the above",2, "Quale frase è un modo informale per dire \"arrivederci\" ad amici o familiari in italiano?"));
        practice.add(new Question(0, "How do you say \"Take care!\" in Italian?", "Stammi bene", "A presto","Ci sentiamo","None of the above",1, "Come si dice \"Abbi cura di te!\" in italiano?"));
        practice.add(new Question(0, "What does \"Ciao bellezza!\" translate to in English?", "Bye, beautiful", "Bye, gorgeous","See you later","None of the above",2, "Cosa significa \"Ciao bellezza!\" tradurre in inglese?"));
        allquestions[0][3] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "In Italian, how is politeness often conveyed when asking for something?", "Using \"per favore\"", "Through the tone of voice","By speaking quickly","None of the above",2, "In italiano, come viene spesso espressa la cortesia nel chiedere qualcosa?"));
        practice.add(new Question(0, "What is one way to sound polite when asking for something in Italian, without using \"per favore\"?", "Put emphasis at the beginning of the request", "Speak louder","Use formal titles","None of the above",1, "Qual è un modo per sembrare educato quando si chiede qualcosa in italiano, senza usare \"per favore\"?"));
        practice.add(new Question(0, "How should you phrase the question \"Could you pass me the oil?\" to make it sound polite in Italian?", "Mi passi l'olio, per favore?", "Mi passi … l’olio?","L'olio, per favore?","None of the above",2, "Come dovresti formulare la domanda \"Potresti passarmi l'olio?\" per farlo sembrare educato in italiano?"));
        allquestions[0][4] = practice;



        practice = new ArrayList<>();
        practice.add(new Question(0, "What does \"dolce\" mean in Italian?", "Salty ", "Sweet ","Spicy ","None of the above",2, "Cosa significa la parola dolce in italiano?"));
        practice.add(new Question(0, "How do you say \"hot/spicy\" in Italian?", "Piccante ", "Caldo ","Freddo ","None of the above",1, "Come si dice \"piccante/piccante\" in italiano?"));
        practice.add(new Question(0, "What is the Italian word for \"cooked\"?", "Crudo ", "Cotto ","Insipido ","None of the above",2, "Qual è la parola italiana per \"cotto\"?"));
        allquestions[1][0] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "How do you ask for a table in Italian?", "Salve! C’è posto per 4?", "Non abbiamo una prenotazione","Accettate la carta?","None of the above",1, "Come si chiede un tavolo in italiano?"));
        practice.add(new Question(0, "Which phrase would you use to ask for more time to decide on your order in Italian?", "Ancora 5 minuti per favore", "Può ripetere per favore?","È tutto squisito","None of the above",1, "Quale frase utilizzeresti per chiedere più tempo per decidere il tuo ordine in italiano?"));
        practice.add(new Question(0, "How do you politely ask for the bill in Italian?", "Ci porta il conto per favore?", "Grazie mille! Alla prossima!","Avete piatti per celiaci?","None of the above",1, "come si fa a chiedere gentilmente il conto in italiano?"));
        allquestions[1][1] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "What does \"Hai il prosciutto sugli occhi\" mean?", "You're being foolish", "You have your head in the sand", "You're everywhere", "None of the above",2, "Cosa significa \"Hai il prosciutto sugli occhi\"?"));
        practice.add(new Question(0, "If someone is described as \"Che pizza!\", what does it mean?", "They are interesting", "They are boring", "They are smart","None of the above",2, "Se qualcuno viene descritto come \"Che pizza!\", cosa significa?"));
        practice.add(new Question(0, "What does \"Sei come il prezzemolo\" imply about a person?", "They are foolish", "They are delicious", "They turn up everywhere" ,"None of the above",3, "Cosa implica \"Sei come il prezzemolo\" su una persona?"));
        allquestions[1][2] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "How do you say \"I'm hungry\" in Italian?", "Non ci vedo più dalla fame", "Ho fame", "Ho una fame da lupo","None of the above",2, "Come si dice \"ho fame\" in italiano?"));
        practice.add(new Question(0, "What does \"Sto morendo di fame\" mean in English?", "I am hungry like a wolf", "I am starving", "I have a hole in my stomach","None of the above",2, "Cosa significa la parola \"Sto morendo di fame\" in inglese?"));
        practice.add(new Question(0,  "What might happen if you tell an Italian grandmother \"ho fame\"?", "She will ignore you", "You'll be asked to leave", "You'll be so full you'll explode","None of the above",3, "Cosa potrebbe succedere se dicessi ad una nonna italiana \"ho fame\"?"));
        allquestions[1][3] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "What is the Italian word for \"carrots\"?", "cipolle", "carote", "cetrioli","None of the above",2, "Qual è la parola italiana per \"carote\"?"));
        practice.add(new Question(0, "Which Italian word means \"lettuce\"?", "lattuga", "cavolo", "cavolfiore" ,"None of the above",1, "Quale parola italiana significa \"lattuga\"?"));
        practice.add(new Question(0, "How do you say \"cucumbers\" in Italian?", "peperoni", "cetrioli", "zucchine","None of the above",2, "Come si dice \"cetrioli\" in italiano?"));
        allquestions[1][4] = practice;



        practice = new ArrayList<>();
        practice.add(new Question(0, "What is the Italian number for \"four\"?", "quattro", "cinque", "sei" ,"None of the above",2, "Qual è la parola italiana per il numero \"15\"?"));
        practice.add(new Question(0, "How do you say \"seventeen\" in Italian?", "diciassette", "diciotto", "diciannove" ,"None of the above",3, "Come si dice \"20\" in italiano?"));
        practice.add(new Question(0, "Which of the following is the Italian word for \"twelve\"?", "dodici", "tredici", "undici" ,"None of the above",2, "Che numero rappresenta \"dodici\" in italiano?"));
        allquestions[2][0] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "How do you say \"1000\" in Italian?", "cento", "mille", "dieci" ,"None of the above",2, "Come esprimono gli italiani il numero decimale 5.3?"));
        practice.add(new Question(0, "What is the Italian term for \"one million\"?", "un milione", "mille", "un miliardo" ,"None of the above",2, "Che punteggiatura usano gli italiani per separare le migliaia?"));
        practice.add(new Question(0, "How is \"one billion\" expressed in Italian?", "un milione", "un miliardo", "centomila" ,"None of the above",2, "Come si scrive il numero 1.584 in italiano?"));
        allquestions[2][1] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "How do you say \"seventh\" in Italian?", "Settimo" , "Sesto" , "Ottavo" ,"None of the above",1, "Come si dice \"settimo\" in italiano?"));
        practice.add(new Question(0, "What is the Italian word for \"first\"?", "Primo" , "Secondo" , "Terzo"  ,"None of the above",1, "Qual è la parola italiana per \"primo\"?"));
        practice.add(new Question(0,  "Which term means \"ninth\" in Italian?", "Nono", "Decimo" , "Quinto"  ,"None of the above",1, "Quale termine significa \"nono\" in italiano?"));
        allquestions[2][2] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "How do Italians express the decimal number 5.3?", "cinque punto tre", "cinque virgola tre", "cinque tre"  ,"None of the above",2, "Come si dice \"1000\" in italiano?"));
        practice.add(new Question(0, "What punctuation do Italians use to separate thousands?", "Virgola", "Punto", "Nessuno dei due (None of the above)" ,"None of the above",1, "Qual è il termine italiano per \"un milione\"?"));
        practice.add(new Question(0,  "How would the number 1,584 be written in Italian?", "1,584", "1.584", "1584" ,"None of the above",2, "Come si esprime \"un miliardo\" in italiano?"));
        allquestions[2][3] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "What is the Italian word for the number \"15\"?", "dieci", "quindici", "venti" ,"None of the above",1, "Qual è il numero italiano di \"quattro\"?"));
        practice.add(new Question(0, "How do you say \"20\" in Italian?", "dieci", "quindici", "venti" ,"None of the above",1, "Come si dice \"diciassette\" in italiano?"));
        practice.add(new Question(0,  "What number does \"dodici\" represent in Italian?", "10", "12", "18"  ,"None of the above",1, "Quale tra le seguenti è la parola italiana per \"dodici\"?"));
        allquestions[2][4] = practice;


        practice = new ArrayList<>();
        practice.add(new Question(0, "How do you say \"black\" in Italian?" , "nero", "bianco", "rosso" ,"Come si dice \"nero\" in italiano? ",1, ""));
        practice.add(new Question(0, "What is the Italian word for \"purple\"?" , "viola" ,"verde", "giallo" ,"Qual è la parola italiana per \"viola\"? ",1, ""));
        practice.add(new Question(0,  "Which color is \"arancione\" in Italian?" , "Green", "Orange" , "Pink" ,"Di che colore è \"arancione\" in italiano? ",2, ""));
        allquestions[3][0] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0,"How do you say \"gray\" in Italian?","grigio","azzurro","marrone"  ,"Come si dice \"grigio\" in italiano? ",1, ""));
        practice.add(new Question(0,"What is the Italian word for \"light blue\"?","blu","celeste","beige"  ,"Qual è la parola italiana per \"azzurro\"? ",2, ""));
        practice.add(new Question(0,"Which color does \"beige\" refer to in Italian?","Blue","Brown","Beige"  ,"A quale colore si riferisce il termine \"beige\" in italiano? ",3, ""));
        allquestions[3][1] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "How do you say \"turquoise\" in Italian?","turchese","lilla","fucsia" ,"Come si dice \"turchese\" in italiano? ",1, ""));
        practice.add(new Question(0, "What is the Italian word for \"lilac\"?","turchese","lilla","fucsia" ,"Qual è la parola italiana per \"lilla\"? ",2, ""));
        practice.add(new Question(0, "Which color is \"fucsia\" in Italian?","Turquoise","Lilac","Fuchsia" ,"Che colore è \"fucsia\" in italiano? ",3, ""));
        allquestions[3][2] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "How do you say \"red\" in Italian?","rosso","verde","rosa" ,"Come si dice \"rosso\" in italiano? ",1, ""));
        practice.add(new Question(0, "What is the Italian word for \"green\"?","rosso","verde","rosa" ,"Qual è la parola italiana per \"verde\"? ",2, ""));
        practice.add(new Question(0, "Which color is \"rosa\" in Italian?","Red","Green","Pink" ,"Di che colore è \"rosa\" in italiano? ",3, ""));
        allquestions[3][3] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "How do you say \"blue\" in Italian?","blu","giallo","azzurro" ,"Come si dice \"blu\" in italiano? ",1, ""));
        practice.add(new Question(0, "What is the Italian word for \"yellow\"?","blu","giallo","azzurro" ,"Qual è la parola italiana per \"giallo\"? ",2, ""));
        practice.add(new Question(0, "Which color is \"azzurro\" in Italian?","Blue","Yellow","Light blue" ,"Che colore è \"azzurro\" in italiano? ",3, ""));
        allquestions[3][4] = practice;




        practice = new ArrayList<>();
        practice.add(new Question(0,  "How do you say \"volleyball\" in Italian?","Calcio","Pallavolo","Pallamano"  ,"Come si dice \"pallavolo\" in italiano? ",2, ""));
        practice.add(new Question(0, "What is the Italian term for \"basketball\"?","Pallacanestro","Tennis","Hockey" ,"Qual è il termine italiano per \"basket\"? ",1, ""));
        practice.add(new Question(0, "Which sport is referred to as \"Football americano\" in Italian?","Soccer","Rugby","American football" ,"Quale sport viene chiamato \"Football americano\" in italiano? ",3, ""));
        allquestions[4][0] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "How do you say \"to swim\" in Italian?","nuotare","giocare a calcio","sciare"  ,"Come si dice \"nuotare\" in italiano? ",1, ""));
        practice.add(new Question(0,  "What is the Italian term for \"to play soccer\"?","nuotare","giocare a calcio","correre" ,"Qual è il termine italiano per \"giocare a calcio\"? ",2, ""));
        practice.add(new Question(0, "Which activity is \"sciare\" in Italian?","Swimming","Soccer","Ski"  ,"Quale attività è \"sciare\" in italiano? ",3, ""));
        allquestions[4][1] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "How do you say \"water polo\" in Italian?","pallanuoto","l'arrampicata","ciclismo" ,"Come si dice \"pallanuoto\" in italiano? ",1, ""));
        practice.add(new Question(0, "What is the Italian term for \"climbing\"?","pallanuoto","l'arrampicata","equitazione"  ,"Qual è il termine italiano per \"arrampicare\"? ",2, ""));
        practice.add(new Question(0, "Which activity is \"equitazione\" in Italian?","Water polo","Cycling","Horse riding" ,"Quale attività è \"equitazione\" in italiano? ",3, ""));
        allquestions[4][2] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0,  "How do you say \"basketball\" in Italian?","pallacanestro","la scherma","il karate"  ,"Come si dice \"basket\" in italiano? ",1, ""));
        practice.add(new Question(0, "What is the Italian term for \"fencing\"?","pallacanestro","la scherma","il karate" ,"Qual è il termine italiano per \"scherma\"? ",2, ""));
        practice.add(new Question(0,  "Which activity is \"il karate\" in Italian?","Basketball","Fencing","Karate" ,"Quale attività è \"il karate\" in italiano? ",3, ""));
        allquestions[4][3] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "How do you say \"skiing\" in Italian?","lo sci","il pugilato","la pallavolo" ,"Come si dice \"sciare\" in italiano? ",1, ""));
        practice.add(new Question(0,  "What is the Italian term for \"boxing\"?","lo sci","il pugilato","la pallavolo" ,"Qual è il termine italiano per \"boxe\"? ",2, ""));
        practice.add(new Question(0, "Which activity is \"la pallavolo\" in Italian?","Skiing","Boxing","Volleyball" ,"Quale attività è \"la pallavolo\" in italiano? ",3, ""));
        allquestions[4][4] = practice;





        practice = new ArrayList<>();
        practice.add(new Question(0, "What is the Italian word for \"Monday\"?","lunedì","martedì","mercoledì" ,"None of the above",1, "no translation available right now"));
        practice.add(new Question(0, "Which day comes after \"venerdì\" in Italian?","sabato","domenica","giovedì" ,"None of the above",1, "no translation available right now"));
        practice.add(new Question(0, "How do you say \"Sunday\" in Italian?","domenica","lunedì","martedì" ,"None of the above",1, "no translation available right now"));
        allquestions[5][0] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "What is the Italian word for \"January\"?","Gennaio","Febbraio","Marzo" ,"None of the above",1, "no translation available right now"));
        practice.add(new Question(0, "Which month comes after \"Settembre\" in Italian?","Ottobre","Novembre","Dicembre" ,"None of the above",1, "no translation available right now"));
        practice.add(new Question(0, "How do you say \"August\" in Italian?","Luglio","Agosto","Settembre"  ,"None of the above",2, "no translation available right now"));
        allquestions[5][1] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "How do you say \"spring\" in Italian?","Primavera","Estate","Autunno" ,"None of the above",1, "no translation available right now"));
        practice.add(new Question(0, "What is the Italian word for \"summer\"?","Primavera","Estate","Inverno" ,"None of the above",2, "no translation available right now"));
        practice.add(new Question(0, "Which season is \"Autunno\" in Italian?","Spring","Summer","Autumn" ,"None of the above",3, "no translation available right now"));
        allquestions[5][2] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "Quante stagioni ci sono in Italia?","Due","Quattro","Sei" ,"None of the above",2, "no translation available right now"));
        practice.add(new Question(0, "Qual è la corretta sequenza delle stagioni in Italia?","Primavera, Estate, Autunno, Inverno","Autunno, Inverno, Primavera, Estate","Estate, Autunno, Inverno, Primavera" ,"None of the above",2, "no translation available right now"));
        practice.add(new Question(0, "Quale stagione segue l'inverno in Italia?", "Estate","Autunno","Primavera" ,"None of the above",3, "no translation available right now"));
        allquestions[5][3] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "Which day of the week is considered the start of the weekend in many cultures?","Venerdì","Sabato","Domenica" ,"None of the above",2, "no translation available right now"));
        practice.add(new Question(0, "What day follows \"martedì\" in the Italian week?","Mercoledì","Giovedì","Venerdì" ,"None of the above",1, "no translation available right now"));
        practice.add(new Question(0, "If \"lunedì\" is the first day of the workweek, what is the last day before the weekend begins?","Mercoledì","Venerdì","Domenica" ,"None of the above",2, "no translation available right now"));
        allquestions[5][4] = practice;





        practice = new ArrayList<>();
        practice.add(new Question(0, "What does \"Che figata!\" express in Italian?","Disgust","Pain","Excitement" ,"None of the above",3, "no translation available right now"));
        practice.add(new Question(0, "How would you describe something that is very nice in Italian?","Che bello!","Che schifo!","Che palle!" ,"None of the above",1, "no translation available right now"));
        practice.add(new Question(0, "Which phrase would you use to express annoyance or frustration in Italian?","Che figata!","Che bello!","Che palle!" ,"None of the above",3, "no translation available right now"));
        allquestions[6][0] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "How do you say \"Good morning\" in Italian?","Buonanotte","Buongiorno","Buonasera" ,"None of the above",2, "no translation available right now"));
        practice.add(new Question(0, "What is the Italian phrase for \"Excuse me, may I get past\"?","Permesso?","Mi scusi","Prego" ,"None of the above",1, "no translation available right now"));
        practice.add(new Question(0, "How would you ask \"Do you speak English?\" formally in Italian?","Come sta?","Parla inglese?","Parlo solo un po' d'italiano" ,"None of the above",2, "no translation available right now"));
        allquestions[6][1] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "What does \"Scusi, possiamo ordinare?\" mean in English?","Excuse me, can we order?","Excuse me, can I have the bill?","Excuse me, is this seat taken?" ,"None of the above",1, "no translation available right now"));
        practice.add(new Question(0, "What drink is being ordered first in the conversation?","Prosecco","Spritz con l'aperol","Water" ,"None of the above",2, "no translation available right now"));
        practice.add(new Question(0, "What is the second drink requested in the dialogue?","A glass of wine","A beer","A glass of prosecco" ,"None of the above",3, "no translation available right now"));
        allquestions[6][2] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "What does \"non prende\" mean regarding phone signal?","Low battery","No signal","Phone is off"  ,"None of the above",2, "no translation available right now"));
        practice.add(new Question(0, "How do you say \"my phone is dead\" in Italian?","Non c'è campo","Ora ho campo","Ho il telefono scarico"  ,"None of the above",3, "no translation available right now"));
        practice.add(new Question(0, "What does \"ora ho campo\" indicate?","Now I'm charging my phone","Now I have the signal","Now my phone is broken" ,"None of the above",2, "no translation available right now"));
        allquestions[6][3] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "How do you say \"I'm on my way\" in Italian?","Sto arrivando","Sono in ritardo","Arrivo tra cinque minuti"  ,"None of the above",1, "no translation available right now"));
        practice.add(new Question(0, "What does \"sono in ritardo\" mean in English?","I'm on time","I'm early","I'm late" ,"None of the above",3, "no translation available right now"));
        practice.add(new Question(0, "How would you tell someone in Italian that you'll arrive in five minutes?","Sto arrivando","Arrivo tra cinque minuti","Tra un attimo sono lì" ,"None of the above",2, "no translation available right now"));
        allquestions[6][4] = practice;




        practice = new ArrayList<>();
        practice.add(new Question(0, "How do you say \"little sister\" in Italian?","sorellina","fratellino","figlia" ,"None of the above",1, "no translation available right now"));
        practice.add(new Question(0, "What is the Italian word for \"son\"?","figlio","figlia","fratello" ,"None of the above",1, "no translation available right now"));
        practice.add(new Question(0, "Which term refers to \"dad\" in Italian?","mamma","papà","sorella" ,"None of the above",2, "no translation available right now"));
        allquestions[7][0] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "How do you say \"my uncle\" in Italian?","mio padre","mia madre","mio zio" ,"None of the above",3, "no translation available right now"));
        practice.add(new Question(0, "What is the Italian term for \"my cousin\" (female)?","mio cugino","mia cugina","mia sorella" ,"None of the above",2, "no translation available right now"));
        practice.add(new Question(0, "Which phrase means \"my sister\" in Italian?","mio fratello","mia sorella","mia zia"  ,"None of the above",2, "no translation available right now"));
        allquestions[7][1] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "What is the Italian word used for both \"niece\" and \"nephew\"?","nipote","cugino","zio" ,"None of the above",1, "no translation available right now"));
        practice.add(new Question(0, "In Italian, how do you say \"granddaughter\"?","nipote","nonna","zia"  ,"None of the above",1, "no translation available right now"));
        practice.add(new Question(0, "What term is used for \"grandson\" in Italian?","nipote","nonno","fratello" ,"None of the above",1, "no translation available right now"));
        allquestions[7][2] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "What does \"genitori\" mean in Italian?","Relatives","Parents","Stepchildren" ,"None of the above",2, "no translation available right now"));
        practice.add(new Question(0, "How do you say \"stepmother\" in Italian?","matrigna","patrigno","figliastra" ,"None of the above",1, "no translation available right now"));
        practice.add(new Question(0, "What is the Italian term for \"children\"?","parenti","figli","genitori" ,"None of the above",2, "no translation available right now"));
        allquestions[7][3] = practice;
        practice = new ArrayList<>();
        practice.add(new Question(0, "What term is used for \"family\" in Italian?","i genitori","la famiglia","fratello" ,"None of the above",2, "no translation available right now"));
        practice.add(new Question(0, "Which word refers to \"sister\" in Italian?","madre","sorella", "padre" ,"None of the above",2, "no translation available right now"));
        practice.add(new Question(0, "What is the Italian word for \"father\"?","madre", "fratello","padre"  ,"None of the above",3, "no translation available right now"));
        allquestions[7][4] = practice;
//
//        practice = new ArrayList<>();
//        practice.add(new Question(0, "Lesson 2 Practice 1 Question 1 Text", "answer a correct", "answer b", "answer c", "answer d",1, "EN Version of the question 211. original question is in Italian"));
//        practice.add(new Question(0, "Lesson 2 Practice 1 Question 2 Text", "answer a correct", "answer b", "answer c", "answer d",1, "EN Version of the question 212. original question is in Italian"));
//        practice.add(new Question(0, "Lesson 2 Practice 1 Question 3 Text", "answer a", "answer b", "answer c", "answer d correct",4, "EN Version of the question 213. original question is in Italian"));
//        practice.add(new Question(0, "Lesson 2 Practice 1 Question 4 Text", "answer a", "answer b", "answer c", "answer d correct",4, "EN Version of the question 214. original question is in Italian"));
//        practice.add(new Question(0, "Lesson 2 Practice 1 Question 5 Text", "answer a", "answer b", "answer c", "answer d correct",4, "EN Version of the question 215. original question is in Italian"));
//        allquestions[1][0] = practice;
//        practice = new ArrayList<>();
//        practice.add(new Question(0, "Lesson 2 Practice 2 Question 1 Text", "answer a", "answer b", "answer c", "answer d correct",4, "EN Version of the question 221. original question is in Italian"));
//        practice.add(new Question(0, "Lesson 2 Practice 2 Question 2 Text", "answer a", "answer b", "answer c", "answer d correct",4, "EN Version of the question 222. original question is in Italian"));
//        allquestions[1][1] = practice;
//
//
//
//        practice = new ArrayList<>();
//        practice.add(new Question(0, "Lesson 3 Practice 1 Question 1 Text", "answer a correct", "answer b", "answer c", "answer d",1, "EN Version of the question 311. original question is in Italian"));
//        practice.add(new Question(0, "Lesson 3 Practice 1 Question 2 Text", "answer a correct", "answer b", "answer c", "answer d",1, "EN Version of the question 312. original question is in Italian"));
//        practice.add(new Question(0, "Lesson 3 Practice 1 Question 3 Text", "answer a", "answer b", "answer c", "answer d correct",4, "EN Version of the question 313. original question is in Italian"));
//        practice.add(new Question(0, "Lesson 3 Practice 1 Question 4 Text", "answer a", "answer b", "answer c", "answer d correct",4, "EN Version of the question 314. original question is in Italian"));
//        practice.add(new Question(0, "Lesson 3 Practice 1 Question 5 Text", "answer a", "answer b", "answer c", "answer d correct",4, "EN Version of the question 315. original question is in Italian"));
//        allquestions[2][0] = practice;
//        practice = new ArrayList<>();
//        practice.add(new Question(0, "Lesson 3 Practice 2 Question 1 Text", "answer a", "answer b", "answer c", "answer d correct",4, "EN Version of the question 321. original question is in Italian"));
//        practice.add(new Question(0, "Lesson 3 Practice 2 Question 2 Text", "answer a", "answer b", "answer c", "answer d correct",4, "EN Version of the question 322. original question is in Italian"));
//        allquestions[2][1] = practice;
//        practice = new ArrayList<>();
//        practice.add(new Question(0, "Lesson 3 Practice 3 Question 1 Text", "answer a", "answer b", "answer c", "answer d correct",4, "EN Version of the question 331. original question is in Italian"));
//        practice.add(new Question(0, "Lesson 3 Practice 3 Question 2 Text", "answer a", "answer b", "answer c", "answer d correct",4, "EN Version of the question 332. original question is in Italian"));
//        allquestions[2][2] = practice;
//
//
//
//
//
//
//        practice = new ArrayList<>();
//        practice.add(new Question(0, "Lesson 4 Practice 1 Question 1 Text", "answer a correct", "answer b", "answer c", "answer d",1, "EN Version of the question 411. original question is in Italian"));
//        practice.add(new Question(0, "Lesson 4 Practice 1 Question 2 Text", "answer a correct", "answer b", "answer c", "answer d",1, "EN Version of the question 412. original question is in Italian"));
//        practice.add(new Question(0, "Lesson 4 Practice 1 Question 3 Text", "answer a", "answer b", "answer c", "answer d correct",4, "EN Version of the question 413. original question is in Italian"));
//        practice.add(new Question(0, "Lesson 4 Practice 1 Question 4 Text", "answer a", "answer b", "answer c", "answer d correct",4, "EN Version of the question 414. original question is in Italian"));
//        practice.add(new Question(0, "Lesson 4 Practice 1 Question 5 Text", "answer a", "answer b", "answer c", "answer d correct",4, "EN Version of the question 415. original question is in Italian"));
//        allquestions[3][0] = practice;
//        practice = new ArrayList<>();
//        practice.add(new Question(0, "Lesson 4 Practice 2 Question 1 Text", "answer a", "answer b", "answer c", "answer d correct",4, "EN Version of the question 421. original question is in Italian"));
//        practice.add(new Question(0, "Lesson 4 Practice 2 Question 2 Text", "answer a", "answer b", "answer c", "answer d correct",4, "EN Version of the question 422. original question is in Italian"));
//        allquestions[3][1] = practice;
        return allquestions;
    }

    public static class Question {
        int questionType;
        String questionText;
        String questionAnswerA;
        String questionAnswerB;
        String questionAnswerC;
        String questionAnswerD;
        int correctAnswer;

        String englishTranslationOfTheQuestion;

        public Question(int type, String questionText, String answerA, String answerB, String answerC, String answerD, int correctAnswer, String translation){
            this.questionType = type;
            this.questionText = questionText;
            this.questionAnswerA = answerA;
            this.questionAnswerB = answerB;
            this.questionAnswerC = answerC;
            this.questionAnswerD = answerD;
            this.correctAnswer = correctAnswer;
            this.englishTranslationOfTheQuestion = translation;
        }

        public Question(int type, String questionText, int correctAnswer){
            this.questionType = type;
            this.questionText = questionText;
            this.correctAnswer = correctAnswer;
        }


    }

    public static class Video{
        int thumbnail;
        String title, url;
        int lessonNumber, videoNumber;
        public Video(String url, int thumbnail, String title, int lessonNumber, int videoNumber){
            this.url = url;
            this.thumbnail = thumbnail;
            this.title = title;
            this.lessonNumber = lessonNumber;
            this.videoNumber = videoNumber;
        }
    }

    public static class Lesson{
        int lessonIcons;
        String lessonTitles;
        int requiredStarsToUnlock;

        public Lesson(int lessonIcons, String lessonTitles, int requiredStarsToUnlock) {
            this.lessonIcons = lessonIcons;
            this.lessonTitles = lessonTitles;
            this.requiredStarsToUnlock = requiredStarsToUnlock;
        }
    }
}
