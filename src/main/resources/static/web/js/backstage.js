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
                fetch(`${GLOBAL_CONFIG.apiEndpoint}/pokeman/1`, {
                    method: 'GET'
                }).then(response => {
                    if (!response.ok) { throw response; }
                    return response.json();
                }).then(hero => {
                    this.hero = hero;
                }).catch(err => {
                    err.json().then(x => { console.error(x.message); });
                });
            },
            updateHero(hero) {
                fetch(`${GLOBAL_CONFIG.apiEndpoint}/pokeman/1`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(hero)
                }).then(response => {
                    if (!response.ok) { throw response; }
                    return response.json();
                }).then(hero => {
                    this.hero = hero;
                }).catch(err => {
                    err.json().then(x => { console.error(x.message); });
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