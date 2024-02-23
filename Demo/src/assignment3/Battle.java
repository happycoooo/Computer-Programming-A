package assignment3;
public class Battle {
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
        int number1 = 1;
        int number2 = 1;
        int mark = 0;
        int cd1Left = cd1;
        int cd2Left = cd2;
        boolean pokemon_dead;
        while (i <= 50 && mark == 0) {
            int speed1 = pokemon1.getSpeed();
            int speed2 = pokemon2.getSpeed();
            pokemon_dead = false;//reset pokemon_dead at the beginning of each round
            if (speed1 < speed2) {//pokemon2 act first
                if (cd2Left == 1) {
                    hp1 -= skillAtk2;
                    cd2Left = cd2;  //reset pokemon2's cd
                } else {
                    hp1 -= atk2;
                    cd2Left -= 1;
                }
                if (hp1 <= 0) {//When p1 lost one, it goes to two situations: give a new or fail
                    pokemon_dead = true;
                    if ((number1) == n1) { //p1's all pokemon dead
                        mark = 2;
                        break;
                    } else {
                        number1 += 1;
                        pokemon1 = p1.pokemons.get(number1 - 1);
                        skill1 = pokemon1.skill;
                        cd1 = skill1.getSkillCd();
                        skillAtk1 = skill1.getSkillAtk();
                        hp1 = pokemon1.getHp();
                        atk1 = pokemon1.getAtk();
                        cd1Left = cd1;
                        cd2Left = cd2;
                    }
                }
                if(!pokemon_dead) {//p1 win this time, pokemon1 go to next round
                    if (cd1Left == 1) {
                        hp2 -= skillAtk1;
                        cd1Left = cd1;
                    } else {
                        hp2 -= atk1;
                        cd1Left -= 1;
                    }
                    if (hp2 <= 0) {
                        if (n2 == number2) {
                            mark = 1;
                            break;
                        } else {
                            number2 += 1;
                            pokemon2 = p2.pokemons.get(number2 - 1);
                            skill2= pokemon2.skill;
                            cd2 = skill2.getSkillCd();
                            skillAtk2 = skill2.getSkillAtk();
                            hp2 = pokemon2.getHp();
                            atk2 = pokemon2.getAtk();
                            cd1Left = cd1;
                            cd2Left = cd2;
                        }
                    }
                }
            } else {
                if (cd1Left == 1) {
                    hp2 -= skillAtk1;
                    cd1Left = cd1;
                } else {
                    hp2 -= atk1;
                    cd1Left -= 1;
                }
                if (hp2 <= 0) {
                    pokemon_dead = true;
                    if ( number2 == n2 ) {
                        mark = 1;
                        break;
                    } else {
                        number2 +=1;
                        pokemon2 = p2.pokemons.get(number2 - 1);
                        skill2 = pokemon2.skill;
                        cd2 = skill2.getSkillCd();
                        skillAtk2 = skill2.getSkillAtk();
                        hp2 = pokemon2.getHp();
                        atk2 = pokemon2.getAtk();
                        cd1Left = cd1;
                        cd2Left = cd2;
                    }
                }if(!pokemon_dead){
                    if (cd2Left == 1) {
                        hp1 -= skillAtk2;
                        cd2Left = cd2;
                    } else {
                        hp1 -= atk2;
                        cd2Left -= 1;
                    }
                    if (hp1 <= 0) {
                        pokemon_dead = true;
                        if (number1 == n1) {
                            mark = 2;
                            break;
                        } else {
                            number1 +=1;
                            pokemon1 = p1.pokemons.get(number1 - 1);
                            skill1 = pokemon1.skill;
                            cd1 = skill1.getSkillCd();
                            skillAtk1 = skill1.getSkillAtk();
                            hp1 = pokemon1.getHp();
                            atk1 = pokemon1.getAtk();
                            cd1Left = cd1;
                            cd2Left = cd2;
                        }
                    }
                }
            }
            i++;
        }
        if (mark == 1) {
            return p1;
        }else if (mark == 2) {
            return p2;
        } else {
            return null;
        }
    }
}


