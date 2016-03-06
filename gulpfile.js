var gulp = require('gulp');
var sass = require('gulp-sass');
var inject = require('gulp-inject');


var root = 'src/main/webapp';
var paths = {
    index: root + '/index.html',
    js: [
        root + '/js/**/*.js',
        '!' + root + '/js/app/App.js'
    ],
    css: [root + '/css/**/*.css'],
    sass: [root + '/scss/**/*.scss']
};

/*
 * gulp inject
 * Injects all app dependencies into Index.html
 */
gulp.task('inject', function () {
    console.log('Updating Index....');
    var index = gulp.src(paths.index);
    var sources = [];
    sources = sources.concat(paths.js);
    sources = sources.concat(paths.css);
    return index
        .pipe(inject(gulp.src(sources), {ignorePath: [root], addRootSlash: false}))
        .pipe(gulp.dest(root));
});

/*
 * gulp sass
 * Compiles sass to css
 */
gulp.task('sass', function () {
    return gulp.src(root + '/scss/**/*.scss')
        .pipe(sass().on('error', sass.logError))
        .pipe(gulp.dest(root + '/css'));
});

/*
 * gulp sass:watch
 * Watches your SASS files for any changes and compiles them automatically to CSS
 */
gulp.task('sass:watch', function () {
    var watcher = gulp.watch(root + '/scss/*.scss', ['sass']);
    watcher.on('change', function (event) {
        console.log('Updating ' + event.path);
    });
});

/*
 * gulp inject:watch
 * Watches your JS & SASS files for any changes, compiles them and injects them into the Index.HTML
 */
gulp.task('inject:watch', function() {
    gulp.watch(paths.sass, ['sass']);
    gulp.watch([
        paths.js,
        paths.css
    ], ['inject']);
});

gulp.task('default', ['sass', 'inject'], function () {
    // place code for your default task here
});