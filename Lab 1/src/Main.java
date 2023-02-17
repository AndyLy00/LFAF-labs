import a.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        StringGenerator stringGenerator = new StringGenerator();
        stringGenerator.generateValidStrings().forEach(System.out::println);
        System.out.println();
        Map<String, List<String>> productions = new HashMap<>();
        productions.put("S", List.of("aB"));
        productions.put("B", List.of("aC", "bB"));
        productions.put("C", List.of("bB", "c", "aS"));
        Set<String> states = new HashSet<>(List.of("S", "B", "C"));
        Grammar grammar = new Grammar(states, new HashSet<>(List.of("a", "b", "c")), "S", productions);
        FiniteAutomation convert = ConvertGrammarToFiniteAutomata.convert(grammar);
        System.out.println(convert.getAlphabet());
        System.out.println();
        Set<String> acceptingStates = new HashSet<>(Arrays.asList("S", "B"));
        String startState = "S";
        Map<String, Map<Character, Set<String>>> transitions = new HashMap<>();
        transitions.put("S", new HashMap<>());
        transitions.get("S").put('a', new HashSet<>(List.of("A")));
        transitions.get("S").put('b', new HashSet<>(List.of("B")));
        transitions.put("B", new HashMap<>());
        transitions.get("B").put('b', new HashSet<>(List.of("S")));
        transitions.get("B").put('c', new HashSet<>(List.of("A")));
        transitions.get("B").put('a', new HashSet<>(List.of("B")));
        transitions.put("C", new HashMap<>());
        transitions.get("C").put('a', new HashSet<>(List.of("B")));
        transitions.get("C").put('b', new HashSet<>(List.of("B")));
        CheckIdIsGood automaton = new CheckIdIsGood(states, acceptingStates, startState, transitions);
        System.out.println(automaton.accepts("aabbbb"));
        System.out.println(automaton.accepts("bbba"));
        System.out.println(automaton.accepts("baaa"));
        System.out.println(automaton.accepts("caac"));
        System.out.println(automaton.accepts("cab"));
    }
}


