$(function () {

    'use strict';

    new Vue({
        el: '.content-wrapper',
        data: {
            hero: {
                status: {
                    hp: 0,
                    attack: 0,
                    defense: 0,
                    specialAttack: 0,
                    specialDefense: 0,
                    speed: 0
                }
            },
            enemy: {
                status: {
                    hp: 0,
                    attack: 0,
                    defense: 0,
                    specialAttack: 0,
                    specialDefense: 0,
                    speed: 0
                }
            },
            round: 0
        },
        methods: {
            getPokeman(isHero) {
                $.ajax({
                    url: `http://localhost:8888/api/pokeman?hero=${isHero}`,
                    method: 'GET'
                }).done(pokeman => {
                    if (isHero) {
                        this.hero = pokeman;
                    } else {
                        this.enemy = pokeman;
                    }
                }).fail(jqXHR => {
                    console.error(JSON.stringify(jqXHR));
                });
            },
            battle() {
                // pokemon damage formula: damage = ((2 * LV + 10) / 250 * (ATK / DEF) * move damage + 2) * bonus
                console.log('===Round ' + ++this.round + '===');
                if (this.round % 2 === 1) {
                    let { attacker, defender } = this.hero.status.speed > this.enemy.status.speed
                        ? { attacker: this.hero, defender: this.enemy }
                        : { attacker: this.enemy, defender: this.hero };
                    let a = (2 * attacker.lv + 10) / 250;
                    console.log(a);
                    let b = attacker.status.attack / defender.status.defense;
                    console.log(b);
                    let dmg = (a * b * 80 + 2) * 1
                    console.log(dmg);
                    defender.status.hp = defender.status.hp - dmg > 0 ? defender.status.hp - dmg : 0;
                } else {
                    let { attacker, defender } = this.hero.status.speed > this.enemy.status.speed
                        ? { attacker: this.enemy, defender: this.hero }
                        : { attacker: this.hero, defender: this.enemy };
                    let a = (2 * attacker.lv + 10) / 250;
                    console.log(a);
                    let b = attacker.status.attack / defender.status.defense;
                    console.log(b);
                    let dmg = (a * b * 80 + 2) * 1
                    console.log(dmg);
                    defender.status.hp = defender.status.hp - dmg > 0 ? defender.status.hp - dmg : 0;
                }
            }
        },
        mounted() {
            this.getPokeman(true);
            this.getPokeman(false);
        }
    });

});