/**
 * GruntJS 配置文件
 *
 * @author: allen jin
 * @date: 2015/11/16
 **/

module.exports = function(grunt) {

  //Initializing the configuration object
  grunt.initConfig({

    pkg: grunt.file.readJSON('package.json'),
    distDir: 'src/main/webapp/static',
    srcDir: 'grunt-static',
    dist: {
      css: '<%= distDir %>/css',
      js: '<%= distDir %>/js',
      fonts: '<%= distDir %>/fonts',
      img: '<%= distDir %>/imgs'
    },

    src: {
      less: '<%= srcDir %>/less/glms.less',
      lessWatch: ['<%= srcDir %>/less/**/*.less', 'src/vendor/bootstrap/**/*.less'],
      fonts: '<%= srcDir %>/fonts',
      js: '<%= srcDir %>/js/**/*.js',
      jsWatch: ['<%= srcDir %>/js/**/*.js'],
      img: '<%= srcDir %>/imgs',
      vendorJs: ['<%= srcDir %>/vendor/**/*.js', '<%= srcDir %>/vendor/**/*.map']
    },
    // Task configuration
    less: {
      //compile less to css
      build: {
        src: '<%= src.less %>',
        dest: '<%= dist.css %>/glms.css'
      }
    },
    //minify the css
    cssmin: {
      build: {
        src: '<%= dist.css %>/glms.css',
        dest: '<%= dist.css %>/glms.min.css'
      }
    },
    //concat files to one
    concat: {
      js: {
        src: '<%= src.js %>',
        dest: '<%= dist.js %>/glms.js'
      }
    },
    uglify: {
      custom: {
        src: '<%= dist.js %>/glms-clean.js',
        dest: '<%= dist.js %>/glms.min.js'
      }
    },
    //文件复制
    copy: {
      vendorJs: {
        src: '<%= src.vendorJs %>',
        expand: true,
        flatten: true,
        dest: '<%= dist.js %>',
        filter: 'isFile'
      },
      fonts: {
        expand: true,
        cwd: '<%= src.fonts %>/',
        src: '**',
        dest: '<%= dist.fonts %>/'
      },
      img: {
        expand: true,
        cwd: '<%= src.img %>/',
        src: '**',
        dest: '<%= dist.img %>/'
      }
    },
    clean: {
      dist: ['<%= distDir %>/*']
    },
    watch: {
      css: {
        files: '<%= src.lessWatch %>',
        tasks: ['less'],
        options: {
          livereload: true
        }
      },
      js: {
        files: ['<%= src.jsWatch %>'],
        tasks: ['jshint', 'concat'],
        options: {
          livereload: true
        }
      },
      resource: {
        files: ['<%= src.img %>/**', '<%= src.fonts %>/**', '<%= src.vendorJs %>'],
        tasks: ['copy'],
        options: {
          livereload: true
        }
      }
    },
    jshint: {
      files: ['gruntfile.js', '<%= src.js %>'],
      options: {
      }
    },
    removelogging: {
      dist: {
        src: '<%= dist.js %>/glms.js',
        dest: '<%= dist.js %>/glms-clean.js',
        options: {

        }
      }
    }
  });

  // Plugin loading
  grunt.loadNpmTasks('grunt-contrib-clean');
  grunt.loadNpmTasks('grunt-contrib-copy');
  grunt.loadNpmTasks('grunt-contrib-concat');
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-less');
  grunt.loadNpmTasks('grunt-autoprefixer');
  grunt.loadNpmTasks('grunt-contrib-cssmin');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-contrib-jshint');
  grunt.loadNpmTasks('grunt-remove-logging');

  //CSS处理任务
  grunt.registerTask('css', ['less', 'cssmin']);

  //JS处理任务
  grunt.registerTask('js', ['jshint', 'concat', 'removelogging', 'uglify']);

  //项目最终发布所用命令
  grunt.registerTask('publish', ['clean', 'css', 'js', 'copy']);

  grunt.registerTask('dev', ['clean', 'less', 'jshint', 'concat', 'copy', 'watch']);

};
