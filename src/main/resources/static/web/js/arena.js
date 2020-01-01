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
            getAttackerAndDefender() {
                if (this.hero.status.speed > this.enemy.status.speed && this.round % 2 === 1) {
                    return { attacker: this.hero, defender: this.enemy };
                } else if (this.hero.status.speed > this.enemy.status.speed && this.round % 2 === 0) {
                    return { attacker: this.enemy, defender: this.hero };
                } else if (this.hero.status.speed < this.enemy.status.speed && this.round % 2 === 1) {
                    return { attacker: this.enemy, defender: this.hero };
                } else if (this.hero.status.speed < this.enemy.status.speed && this.round % 2 === 0) {
                    return { attacker: this.hero, defender: this.enemy };
                }
            },
            battle() {
                // pokemon damage formula: damage = ((2 * LV + 10) / 250 * (ATK / DEF) * move damage + 2) * bonus
                console.log('===Round ' + ++this.round + '===');
                let { attacker, defender } = this.getAttackerAndDefender();
                let a = (2 * attacker.lv + 10) / 250;
                console.log(a);
                let b = attacker.status.attack / defender.status.defense;
                console.log(b);
                let dmg = Math.round((a * b * 80 + 2) * 1);
                console.log(dmg);
                Swal.fire({
                    titleText: `Round ${this.round}`,
                    text: `${attacker.name} deals ${dmg} damage to ${defender.name}.`
                });
                defender.status.hp = defender.status.hp - dmg > 0 ? defender.status.hp - dmg : 0;
            }
        },
        mounted() {
            this.getPokeman(true);
            this.getPokeman(false);
        }
    });

});