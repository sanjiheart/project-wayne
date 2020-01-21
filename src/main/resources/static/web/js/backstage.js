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
                axios({
                    url: `${GLOBAL_CONFIG.apiEndpoint}/pokeman/1`,
                    method: 'GET'
                }).then(response => {
                    this.hero = response.data;
                }).catch(err => {
                    console.error(err);
                });
            },
            updateHero(hero) {
                $('#save').prop('disabled', true);
                axios({
                    url: `${GLOBAL_CONFIG.apiEndpoint}/pokeman/1`,
                    method: 'PUT',
                    body: hero
                }).then(response => {
                    $('#save').prop('disabled', false);
                    this.hero = response.data;
                }).catch(err => {
                    $('#save').prop('disabled', false);
                    console.error(err);
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