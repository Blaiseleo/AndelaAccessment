package com.example;

import java.util.*;


class CountFruits {

    private static final String RESPONSE_TYPE_DEFAULT = "default";
    private static final String RESPONSE_TYPE_ADVANCED = "advanced";

    public static void main(String[] args) {

        String[] words = new String[]{"Strawberry", "Mango", "Cherry", "Lime", "Guava",
                "Papaya", "Nectarine", "Pineapple", "Lemon", "Plum", "Tangerine", "Fig",
                "Blueberry", "Grape", "Jackfruit", "Pomegranate", "Apple", "Pear",
                "Orange", "Watermelon", "Raspberry", "Banana"};

        //Implementation to get request from external API
        String[] wordsFromAPI = getFruitsFromAPI();


        //Select the response type to run with i.e RESPONSE_TYPE_DEFAULT || RESPONSE_TYPE_ADVANCED
        countFruits(words, RESPONSE_TYPE_DEFAULT);
        countFruits(words, RESPONSE_TYPE_ADVANCED);

    }

    private static void countFruits (String[] words, String responseType){

        if(words == null || words.length == 0 || words[0].length() == 0){
            return;
        }

        Map<Character, List<String>> store = initSetUp();
        store = populateStore(store, words);
        printResponse(store, responseType);
    }

    private static Map<Character, List<String>> initSetUp(){
        Map<Character, List<String>> store = new HashMap<>();

        char[] alphabets = new char[]{'A','B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        for(char c : alphabets){
            store.put(c, new ArrayList<>());
        }

        return store;
    }

    private static Map<Character, List<String>> populateStore(Map<Character, List<String>> store, String[] words){
        for(String str : words){

            if(store.containsKey(str.charAt(0))){

                char tempChar = str.charAt(0);

                List<String> tempStore = store.get(str.charAt(0));
                tempStore.add(str);

                store.put(tempChar, tempStore);
            }
        }
        return store;
    }

    private static void printResponse(Map<Character, List<String>> store, String responseType){
        if("default".equals(responseType)){
            printDefaultResponse(store);
        }else if("advanced".equals(responseType)){
            printAdvancedResponse(store);
        }
    }


    private static void printDefaultResponse(Map<Character, List<String>> store){
        ResponseBuilder builder = new ResponseBuilder();
        for(Map.Entry<Character, List<String>> entry : store.entrySet()){
            builder.setCharacter(entry.getKey());
            builder.setCount(entry.getValue().size());
            System.out.println(builder.getCharacter()+": "+builder.getCount());
            System.out.println();
        }
    }

    private static void printAdvancedResponse(Map<Character, List<String>> store){

        for(Map.Entry<Character, List<String>> entry : store.entrySet()){

            ResponseBuilder builder = new ResponseBuilder();
            builder.setCharacter(entry.getKey());
            builder.setCount(entry.getValue().size());
            System.out.println(builder.getCharacter()+": "+builder.getCount());
            System.out.println();

            Map<String, Integer> res = new HashMap<>();

            for(String str : entry.getValue()){
                if(res.containsKey(str)){
                    res.put(str, res.get(str) + 1);
                }else{
                    res.put(str, 1);
                }
            }

            for(Map.Entry<String, Integer> en : res.entrySet()){
                System.out.println(en.getValue()+" "+en.getKey()+"\n");
            }
        }

    }

    private static String[] getFruitsFromAPI(){
        try{
            //Implementation to get fruits from external API
        } catch (Exception e) {
            //log error message
            //throw a predefined exception (i.e BadRequestException(), ServerErrorException()..);
        }

        return new String[]{};
    }

    static class ResponseBuilder{
        private char character;
        private int count;

        ResponseBuilder(){}

        ResponseBuilder(char character, int count){
            this.character = character;
            this.count = count;
        }

        private void setCharacter(char character){
            this.character = character;
        }

        private char getCharacter(){
            return character;
        }

        private int getCount(){
            return count;
        }

        private void setCount(int count){
            this.count = count;
        }
    }
}
