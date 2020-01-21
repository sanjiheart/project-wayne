const { src, dest, series, parallel } = require('gulp'),
    yarn = require('gulp-yarn'),
    minifyHtml = require('gulp-minify-html'),
    uglifyJs = require('gulp-uglify-es').default,
    merge = require('merge-stream'),
    inject = require('gulp-inject'),
    clean = require('gulp-clean');

const SRC_PATH = 'web',
    BUILD_PATH = 'src/main/resources/static/web',
    SRC_LIBS_PATH = `${SRC_PATH}/node_modules`,
    BUILD_LIBS_PATH = `${BUILD_PATH}/node_modules`;

function execYarn() {
    return src(`${SRC_PATH}/package.json`)
        .pipe(yarn());
}

function copyFiles() {
    return merge(
        src(`${SRC_PATH}/favicon.ico`).pipe(dest(BUILD_PATH))
        , src(`${SRC_PATH}/imgs/*`).pipe(dest(`${BUILD_PATH}/imgs`))
        , src(`${SRC_PATH}/sounds/*`).pipe(dest(`${BUILD_PATH}/sounds`))
    );
}

function copyLibs() {
    return merge(
        src(`${SRC_LIBS_PATH}/bootstrap/dist/**`).pipe(dest(`${BUILD_LIBS_PATH}/bootstrap/dist`))
        , src(`${SRC_LIBS_PATH}/@fortawesome/fontawesome-free/**`).pipe(dest(`${BUILD_LIBS_PATH}/@fortawesome/fontawesome-free`))
        , src(`${SRC_LIBS_PATH}/admin-lte/dist/**`).pipe(dest(`${BUILD_LIBS_PATH}/admin-lte/dist`))
        , src(`${SRC_LIBS_PATH}/animate.css/animate.min.css`).pipe(dest(`${BUILD_LIBS_PATH}/animate.css`))
        , src(`${SRC_LIBS_PATH}/vue/dist/**`).pipe(dest(`${BUILD_LIBS_PATH}/vue/dist`))
        , src(`${SRC_LIBS_PATH}/jquery/dist/**`).pipe(dest(`${BUILD_LIBS_PATH}/jquery/dist`))
        , src(`${SRC_LIBS_PATH}/axios/dist/**`).pipe(dest(`${BUILD_LIBS_PATH}/axios/dist`))
        , src(`${SRC_LIBS_PATH}/sweetalert2/dist/**`).pipe(dest(`${BUILD_LIBS_PATH}/sweetalert2/dist`))
    );
}

function minifyJs() {
    return src(`${SRC_PATH}/js/*.js`)
        .pipe(uglifyJs().on('error', function (e) {
            console.log(e);
        }))
        .pipe(dest(`${BUILD_PATH}/js`));
}

function injectLibs() {
    let libs = src([`${SRC_LIBS_PATH}/bootstrap/dist/css/bootstrap.min.css`
        , `${SRC_LIBS_PATH}/@fortawesome/fontawesome-free/css/all.min.css`
        , `${SRC_LIBS_PATH}/admin-lte/dist/css/adminlte.min.css`
        , `${SRC_LIBS_PATH}/animate.css/animate.min.css`
        , `${SRC_LIBS_PATH}/sweetalert2/dist/sweetalert2.min.css`
        , `${SRC_LIBS_PATH}/vue/dist/vue.js`
        , `${SRC_LIBS_PATH}/jquery/dist/jquery.slim.min.js`
        , `${SRC_LIBS_PATH}/axios/dist/axios.min.js`
        , `${SRC_LIBS_PATH}/bootstrap/dist/js/bootstrap.min.js`
        , `${SRC_LIBS_PATH}/@fortawesome/fontawesome-free/js/all.min.js`
        , `${SRC_LIBS_PATH}/admin-lte/dist/js/adminlte.min.js`
        , `${SRC_LIBS_PATH}/sweetalert2/dist/sweetalert2.min.js`
        , `${SRC_PATH}/js/global.js`
    ]);
    return src(`${SRC_PATH}/*.html`)
        .pipe(inject(libs, { ignorePath: SRC_PATH, addRootSlash: false }))
        .pipe(minifyHtml())
        .pipe(dest(`${BUILD_PATH}`));
}

function cleanAll() {
    return src(BUILD_PATH, { allowEmpty: true })
        .pipe(clean());
}

exports.build = series(cleanAll, execYarn, parallel(copyFiles, copyLibs, minifyJs, injectLibs));