package ui;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Solution {
	static String alg = null;
	static String heu = null;
	static String stsp = null;
	static Map<String, Map<String,BigDecimal>> svijet = null;
	static Map<String, BigDecimal> heuristicka = null;
	static String ps = null;
	static List<String> zs = null;
	static boolean co=false;
	static boolean cc=false;
	static String ime_heu;
	public static void main(String ... args) throws IOException {
            System.setOut(new java.io.PrintStream(System.out, true, StandardCharsets.UTF_8));
            System.setErr(new java.io.PrintStream(System.err, true, StandardCharsets.UTF_8));

		for(int i=0;i<args.length;i++){

			if(args[i].equals("--alg")){
				alg=args[++i];
			}
			else if(args[i].equals("--ss")){
				/*if(args[i+1].equals("3x3_puzzle.txt")){
					return;
				}*/
				stsp="C:\\Users\\Korisnik\\Desktop\\uui\\autograder\\data\\lab1\\files\\"+args[++i];
			}
			else if(args[i].equals("--h")){
				ime_heu=args[++i];
				heu="C:\\Users\\Korisnik\\Desktop\\uui\\autograder\\data\\lab1\\files\\"+ime_heu;
			}
			else if(args[i].equals("--check-optimistic")){
				co=true;
			}
			else if(args[i].equals("--check-consistent")){
				cc=true;
			}

		}
		if(heu!=null){
			heuristicka=new TreeMap<>();
			List<String> lines = Files.readAllLines(Paths.get(heu));
			for(String line:lines){
				heuristicka.put(line.split(": ")[0],new BigDecimal(line.split(": ")[1]));
			}
		}
		if(stsp!=null){
			svijet=new HashMap<>();
			List<String> lines = Files.readAllLines(Paths.get(stsp));
			int i=0;
			while(i<lines.size()){
				if(!lines.get(i).equals("#")){
					break;
				}
				i++;
			}
			ps=lines.get(i++);

			zs= List.of(lines.get(i++).split(" "));

			for(;i< lines.size();i++)
			{
				if(!lines.get(i).equals("#") && lines.get(i).split(": ").length>1){
					Map<String,BigDecimal> mapa=new TreeMap<>();
					//System.out.println(lines.get(i).split(": ").length);
					for (String s : lines.get(i).split(": ")[1].split(" ")) {
						/*svijet.put(lines.get(i).split(": ")[0] + "-" + s.split(",")[0], new BigDecimal(s.split(",")[1]));*/
						mapa.put(s.split(",")[0],new BigDecimal(s.split(",")[1]));
					}
					svijet.put(lines.get(i).split(": ")[0],mapa);
				}

			}
		}
		if(alg!=null){
			if(alg.equals("astar")){
				astar();
			}
			else if(alg.equals("bfs")){
				bfc();
			}
			else if(alg.equals("ucs")){
				ucs(ps, true);
			}
		}
		if(co){
			provjeri_opt();
		}
		if(cc){
			provjeri_con();
		}
		try {
			System.out.flush();
			System.err.flush();
			Thread.sleep(500);  // Daj malo vremena da autograder sve preuzme
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		return;
	}

	private static void provjeri_con() {
		System.out.println("# HEURISTIC-CONSISTENT "+ime_heu);
		boolean bol=true;
		for(String state:heuristicka.keySet()){
                        if(svijet.containsKey(state)){
                            for(String state1:svijet.get(state).keySet()){
                                    if(heuristicka.get(state).compareTo(heuristicka.get(state1).add(svijet.get(state).get(state1)))<=0){
                                            System.out.println("[CONDITION]: [OK] h("+state+") <= h("+state1+") + c: "+heuristicka.get(state).doubleValue()+" <= "+heuristicka.get(state1).doubleValue()+" + "+svijet.get(state).get(state1).doubleValue());
                                    }
                                    else {
                                            bol=false;
                                            System.out.println("[CONDITION]: [ERR] h("+state+") <= h("+state1+") + c: "+heuristicka.get(state).doubleValue()+" <= "+heuristicka.get(state1).doubleValue()+" + "+svijet.get(state).get(state1).doubleValue());
                                    }

                            }
                        }
		}
		System.out.println("[CONCLUSION]: Heuristic is "+(bol?"":"not ")+"consistent.");
	}

	private static void provjeri_opt() {
		System.out.println("# HEURISTIC-OPTIMISTIC "+ime_heu);
		boolean bol=true;
		for(String state:heuristicka.keySet()){
			if(heuristicka.get(state).compareTo(ucs(state,false))<=0){
				System.out.println("[CONDITION]: ["+"OK"+"] h("+state+") <= h*: "+heuristicka.get(state).doubleValue()+" <= "+ucs(state,false).doubleValue());
			}
			else{
				bol=false;
				System.out.println("[CONDITION]: ["+"ERR"+"] h("+state+") <= h*: "+heuristicka.get(state).doubleValue()+" <= "+ucs(state,false).doubleValue());
			}
		}
		System.out.println("[CONCLUSION]: Heuristic is "+(bol?"":"not ")+"optimistic.");
	}


	private static void bfc() {
		System.out.println("# BFS");
		Set<State> visited=new HashSet<>();
                Set<String> fropom=new HashSet<>();
		Queue<State> frontier=new LinkedList<>();
		State current=new State(ps,null,new BigDecimal(0));
		frontier.add(current);
                fropom.add(ps);
		if(zs.contains(current.getState())){
			ispis_pocenta(ps);
			return;
		}

		while(!frontier.isEmpty()){


			current=frontier.poll();
                        fropom.remove(current.getState());
			visited.add(current);
			Map<String,BigDecimal> mapa=svijet.get(current.getState());
			List<State> pomocna=new ArrayList<>();

			for(String key:mapa.keySet()){
				State st=new State(key,current,mapa.get(key).add(current.getCost()));
				if(zs.contains(st.getState())){
					ispis(st,visited.size()+frontier.size()+1+pomocna.size(), true);
					return;
				}
				if(!visited.contains(st) && !fropom.contains(key)){
					pomocna.add(st);
                                        fropom.add(key);
				}


			}
			pomocna.sort(Comparator.comparing(State::getState));

			frontier.addAll(pomocna);

		}
		System.out.println("[FOUND_SOLUTION]: no");



	}

	private static void ispis_pocenta(String ps) {
		System.out.println("[FOUND_SOLUTION]: yes");
		System.out.println("[STATES_VISITED]: "+1);
		System.out.println("[PATH_LENGTH]: "+0);
		System.out.println("[TOTAL_COST]: "+0.0);
		System.out.println("[PATH]: "+ps);
	}

	private static BigDecimal ispis(State st, int size, boolean b) {
		String str="";
		str+=st.getState();
		State pomocna=st.getParent();
		int path_size=1;
		if(b){
			while(pomocna!=null){
				str=pomocna.getState()+" => "+str;
				pomocna=pomocna.getParent();
				path_size++;
			}
			System.out.println("[FOUND_SOLUTION]: yes");
			System.out.println("[STATES_VISITED]: "+size);
			System.out.println("[PATH_LENGTH]: "+path_size);
			System.out.println("[TOTAL_COST]: "+st.getCost().doubleValue());
			System.out.println("[PATH]: "+str);
		}

		return st.getCost();
	}

	private static BigDecimal ucs(String ps, boolean b) {
		if(b) {
			System.out.println("# UCS");
		}
		Set<State> visited=new HashSet<>();
		PriorityQueue<State> frontier=new PriorityQueue<>(Comparator.comparing(State::getCost).thenComparing(State::getState));
		Map<String,BigDecimal> fropom=new HashMap<>();
		State current=new State(ps,null,new BigDecimal(0));
		frontier.add(current);
		fropom.put(ps,new BigDecimal(0));
		if(zs.contains(current.getState())){
			ispis_pocenta(ps);
			return new BigDecimal(0);
		}

		while(!frontier.isEmpty()){

			current=frontier.poll();
			visited.add(current);
			fropom.remove(current.getState());
			if(zs.contains(current.getState())){
				BigDecimal bg=ispis(current,visited.size(),b);
				return bg;
			}
			Map<String,BigDecimal> mapa=svijet.get(current.getState());
			for(String key:mapa.keySet()){
				State st=new State(key,current,mapa.get(key).add(current.getCost()));
				if(!visited.contains(st)){
					BigDecimal bd=fropom.get(key);
					if(bd==null){
						frontier.add(st);
						fropom.put(key,st.getCost());
					}
					else{
						if(bd.compareTo(st.getCost())>0){
							fropom.put(key,bd);
							frontier.remove(st);
							frontier.add(st);
						}

					}

				}

			}



		}
		System.out.println("[FOUND_SOLUTION]: no");

		return BigDecimal.valueOf(0);

	}
	private static void astar() {
		System.out.println("# ASTAR "+ime_heu);
		Set<State> visited=new HashSet<>();
		PriorityQueue<State> frontier=new PriorityQueue<>(Comparator
				.comparing((State s) -> heuristicka.get(s.getState()).add(s.getCost()))
				.thenComparing(State::getState));
		Map<String,BigDecimal> fropom=new HashMap<>();
		State current=new State(ps,null,new BigDecimal(0));
		frontier.add(current);
		fropom.put(ps,new BigDecimal(0));
		if(zs.contains(current.getState())){
			ispis_pocenta(ps);
			return ;
		}
		while(!frontier.isEmpty()){
			current=frontier.poll();
			visited.add(current);
			fropom.remove(current.getState());
			if(zs.contains(current.getState())){
				BigDecimal bg=ispis(current,visited.size(),true);
				return ;
			}
			Map<String,BigDecimal> mapa=svijet.get(current.getState());
			for(String key:mapa.keySet()){
				State st=new State(key,current,mapa.get(key).add(current.getCost()));
				if(!visited.contains(st)){
					BigDecimal bd=fropom.get(key);
					if(bd==null){
						frontier.add(st);
						fropom.put(key,st.getCost());
					}
					else{
						if(bd.compareTo(st.getCost())>0){
							fropom.put(key,bd);
							frontier.remove(st);
							frontier.add(st);
						}

					}

				}

			}



		}
		System.out.println("[FOUND_SOLUTION]: no");

		return ;
	}



}
