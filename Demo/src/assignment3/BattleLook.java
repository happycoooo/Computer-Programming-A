package assignment3;
public class BattleLook {
    public static Player tatakai(Player p1, Player p2) {
        int cd1, skillAtk1;
        int cd2, skillAtk2;
        int hp1, hp2;
        int atk1, atk2;
        int i = 1;
        Pokemon pokemon1 = p1.pokemons.get(0);
        Pokemon pokemon2 = p2.pokemons.get(0);
        Skill skill1 = pokemon1.skill;
        Skill skill2 = pokemon2.skill;
        cd1 = skill1.getSkillCd();
        cd2 = skill2.getSkillCd();
        skillAtk1 = skill1.getSkillAtk();
        skillAtk2 = skill2.getSkillAtk();
        hp1 = pokemon1.getHp();
        hp2 = pokemon2.getHp();
        atk1 = pokemon1.getAtk();
        atk2 = pokemon2.getAtk();
        int n1 = p1.pokemons.size();
        int n2 = p2.pokemons.size();
        //System.out.println("n1:" + n1 + " n2:" + n2);
        int number1 = 1;
        int number2 = 1;
        int mark = 0;
        int cd1_left = cd1;
        int cd2_left = cd2;
        boolean pokemon_dead = false;
        while (i <= 50 && mark == 0) {
           /* System.out.println();
            System.out.println("round" + i);
            System.out.println("hp1: " + hp1 + " hp2: " + hp2);
            System.out.println("cd1_left: " + cd1_left + " cd2_left: " + cd2_left);*/
            int speed1 = pokemon1.getSpeed();
            int speed2 = pokemon2.getSpeed();
            pokemon_dead = false; // reset pokemon_dead at the beginning of each round
            if (speed1 < speed2) { // pokemon2 act first
                if (cd2_left == 1) {
                    hp1 -= skillAtk2;
                    cd2_left = cd2; // reset cd of pokemon2
                } else {
                    hp1 -= atk2;
                    cd2_left -= 1;
                }
                if (hp1 <= 0) {
                    pokemon_dead = true;
                    if (number1 == n1) { // all pokemons of player1 dead
                        mark = 2;
                        break;
                    } else {
                        number1 += 1;
                        pokemon1 = p1.pokemons.get(number1 - 1); //get next pokemon of player1
                        skill1 = pokemon1.skill;
                        cd1 = skill1.getSkillCd();
                        skillAtk1 = skill1.getSkillAtk();
                        hp1 = pokemon1.getHp();
                        atk1 = pokemon1.getAtk();
                        cd1_left = cd1; // reset cd
                        cd2_left = cd2;
                    }
                }
                if (!pokemon_dead) { // if pokemon1 has been changed, go next round
                    if (cd1_left == 1) { // pokemon1 act now
                        hp2 -= skillAtk1;
                        cd1_left = cd1; // reset cd of pokemon2
                    } else {
                        hp2 -= atk1;
                        cd1_left -= 1;
                    }
                    if (hp2 <= 0) {
                        if (number2 == n2) { // all pokemons of player2 dead
                            mark = 1;
                            break;
                        } else {
                            number2 += 1;
                            pokemon2 = p2.pokemons.get(number2 - 1); //get next pokemon of player2
                            skill2 = pokemon2.skill;
                            cd2 = skill2.getSkillCd();
                            skillAtk2 = skill2.getSkillAtk();
                            hp2 = pokemon2.getHp();
                            atk2 = pokemon2.getAtk();
                            cd2_left = cd2; // reset cd
                            cd1_left = cd1;
                        }
                    }
                }
            } else {
                if (cd1_left == 1) { // pokemon1 act first
                    hp2 -= skillAtk1;
                    cd1_left = cd1; // reset cd of pokemon2
                } else {
                    hp2 -= atk1;
                    cd1_left -= 1;
                }
                if (hp2 <= 0) {
                    pokemon_dead = true;
                    if (number2 == n2) { // all pokemons of player2 dead
                        mark = 1;
                        break;
                    } else {
                        number2 += 1;
                        pokemon2 = p2.pokemons.get(number2 - 1); //get next pokemon of player2
                        skill2 = pokemon2.skill;
                        cd2 = skill2.getSkillCd();
                        skillAtk2 = skill2.getSkillAtk();
                        hp2 = pokemon2.getHp();
                        atk2 = pokemon2.getAtk();
                        cd2_left = cd2; // reset cd
                        cd1_left = cd1;
                    }
                }
                if (!pokemon_dead) { // if pokemon2 has been changed, go next round
                    if (cd2_left == 1) { // pokemon2 act now
                        hp1 -= skillAtk2;
                        cd2_left = cd2; // reset cd of pokemon2
                    } else {
                        hp1 -= atk2;
                        cd2_left -= 1;
                    }
                    if (hp1 <= 0) {
                        pokemon_dead = true;
                        if (number1 == n1) { // all pokemons of player1 dead
                            mark = 2;
                            break;
                        } else {
                            number1 += 1;
                            pokemon1 = p1.pokemons.get(number1 - 1); //get next pokemon of player1
                            skill1 = pokemon1.skill;
                            cd1 = skill1.getSkillCd();
                            skillAtk1 = skill1.getSkillAtk();
                            hp1 = pokemon1.getHp();
                            atk1 = pokemon1.getAtk();
                            cd1_left = cd1; // reset cd
                            cd2_left = cd2;
                        }
                    }
                }
            }
            //System.out.println("hp1: " + hp1 + " hp2: " + hp2);
            i++;
        }
        if (mark == 1) {
            return p1;
        } else if (mark == 2) {
            return p2;
        } else {
            return null;
        }
    }
}
    /*public static void main(String[] args) {
        Player player1 = new Player(new Mail("1@mail.sustech.edu.cn"), new
                PhoneNumber("1"), "1");
        Player player2 = new Player(new Mail("2@mail.sustech.edu.cn"),new PhoneNumber("2"),
                "2");
        Skill skill1 = new Skill("skill1", 2, 3);
        Skill skill2 = new Skill("skill2", 3, 2);
        Pokemon pokemon1 = new Pokemon("pokemon1", 10, 1, skill1, 1, 1, 3, 3);
        Pokemon pokemon2 = new Pokemon("pokemon2", 10, 1, skill2, 1, 2, 3, 3);
        player1.addPokemon(pokemon1);


        player2.addPokemon(pokemon2);
        player2.addPokemon(pokemon1);

        Player winner = tatakai(player1, player2);
        //System.out.println(winner);
        System.out.println("player" + winner.getPhoneNumber().phoneNumber + " wins");
    }*/

