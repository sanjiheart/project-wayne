$(function () {

    'use strict';

    new Vue({
        el: '.content-wrapper',
        data: {
            hero: {
                name: 'Wayne',
                status: {
                    hp: 0,
                    attack: 0,
                    defense: 0,
                    specialAttack: 0,
                    specialDefense: 0,
                    speed: 0
                },
                photoUrl: 'imgs/hero.jpg'
            },
            enemy: {
                name: '???',
                status: {
                    hp: 0,
                    attack: 0,
                    defense: 0,
                    specialAttack: 0,
                    specialDefense: 0,
                    speed: 0
                },
                photoUrl: 'imgs/enemy.png'
            },
            round: 0,
            battleAudio: new Audio('sounds/battle.wav'),
            atkAudio: new Audio('sounds/attack.wav'),
            sAtkAudio: new Audio('sounds/special-attack.wav')
        },
        methods: {
            getPokeman(no) {
                fetch(`${GLOBAL_CONFIG.apiEndpoint}/pokeman/${no}`, {
                    method: 'GET'
                }).then(response => {
                    if (!response.ok) { throw response }
                    return response.json();
                }).then(pokeman => {
                    if (no === 1) {
                        this.hero = pokeman;
                    } else {
                        this.enemy = pokeman;
                        $(`img[alt="hero-pic"]`).removeClass('animated shake');
                        $(`img[alt="enemy-pic"]`).removeClass('animated shake');
                        $('#battle').prop('disabled', false);
                    }
                }).catch(err => {
                    err.json().then(x => { console.error(x.message); });
                });
            },
            playBattleAudio() {
                let $svg = $(event.currentTarget).find('svg');
                if ($svg.hasClass('fa-volume-mute')) {
                    this.battleAudio.pause();
                    $svg.removeClass('fa-volume-mute').addClass('fa-play-circle');
                } else {
                    this.battleAudio.loop = true;
                    this.battleAudio.play();
                    $svg.removeClass('fa-play-circle').addClass('fa-volume-mute');
                }
            },
            getAttackerAndDefender() {
                if (this.hero.status.speed >= this.enemy.status.speed && this.round % 2 === 1) {
                    return { attacker: this.hero, defender: this.enemy };
                } else if (this.hero.status.speed >= this.enemy.status.speed && this.round % 2 === 0) {
                    return { attacker: this.enemy, defender: this.hero };
                } else if (this.hero.status.speed < this.enemy.status.speed && this.round % 2 === 1) {
                    return { attacker: this.enemy, defender: this.hero };
                } else if (this.hero.status.speed < this.enemy.status.speed && this.round % 2 === 0) {
                    return { attacker: this.hero, defender: this.enemy };
                }
            },
            battle() {
                // pokemon damage formula: damage = ((2 * LV + 10) / 250 * (ATK / DEF) * move damage + 2) * bonus
                let { attacker, defender } = this.getAttackerAndDefender();
                let a = (2 * attacker.lv + 10) / 250;
                let b = attacker.status.attack >= attacker.status.specialAttack
                    ? attacker.status.attack / defender.status.defense
                    : attacker.status.specialAttack / defender.status.specialDefense;
                attacker.status.attack >= attacker.status.specialAttack ? this.atkAudio.play() : this.sAtkAudio.play();
                let dmg = Math.round((a * b * 80 + 2) * 1);
                $(`img[src="${attacker.photoUrl}"`).removeClass('animated shake');
                $(`img[src="${defender.photoUrl}"`).addClass('animated shake');
                defender.status.hp = defender.status.hp - dmg > 0 ? defender.status.hp - dmg : 0;
                if (defender.status.hp === 0) {
                    Swal.fire({
                        titleText: `Congratulations to ${attacker.name}`,
                        text: `${defender.name} has been K.O.`,
                        icon: 'success',
                        iconHtml: '<i class="fas fa-trophy"></i>',
                        confirmButtonText: 'Good Job'
                    });
                    // reset
                    this.round = 0;
                    $('#battle').prop('disabled', true);
                } else {
                    Swal.fire({
                        titleText: `Round ${++this.round}`,
                        text: `${attacker.name} deals ${dmg} damage to ${defender.name}.`,
                        toast: true,
                        position: 'bottom',
                        timer: 3000,
                        timerProgressBar: true,
                        showConfirmButton: false
                    });
                }
            }
        },
        mounted() {
            this.getPokeman(1);
        }
    });

});