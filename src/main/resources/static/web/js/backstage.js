$(function () {

    'use strict';

    new Vue({
        el: '.content-wrapper',
        data: {
            hero: {
                status: {}
            }
        },
        methods: {
            getHero() {
                $.ajax({
                    url: `${GLOBAL_CONFIG.apiEndpoint}/pokeman/1`,
                    method: 'GET'
                }).done(hero => {
                    this.hero = hero;
                }).fail(jqXHR => {
                    console.error(JSON.stringify(jqXHR));
                });
            },
            updateHero(hero) {
                $.ajax({
                    url: `${GLOBAL_CONFIG.apiEndpoint}/pokeman/1`,
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    data: JSON.stringify(hero)
                }).done(hero => {
                    this.hero = hero;
                }).fail(jqXHR => {
                    console.error(JSON.stringify(jqXHR));
                });
            },
            save() {
                console.log(JSON.stringify(this.hero));
                this.updateHero(this.hero);
            }
        },
        mounted() {
            this.getHero();
        }
    });

});