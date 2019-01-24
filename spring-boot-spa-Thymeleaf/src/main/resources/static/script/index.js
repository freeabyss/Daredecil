$(function () {
    vipspa.start({
        view: '#view-ui',
        errorTemplateId: '#error', // 可选
        router: {
            'home': {
                templateUrl: 'home',
                controller: 'static/script/home.js'
            },
            'contact': {
                templateUrl: 'contact',
                controller: 'static/script/contact.js'
            },
            'feature': {
                templateUrl: 'feature',
                controller: 'static/script/feature.js'
            },
            // 'contact': {
            //     templateUrl: 'views/contact.html',
            //     controller: 'js/app/contact.js'
            // },
            // 'user': {
            //     templateUrl: 'views/user.html',
            //     controller: 'js/app/user.js',
            //     subView: '#ui-sub-view',
            //     children: [
            //         {
            //             name: 'login',
            //             templateUrl: 'views/login.html',
            //             controller: 'js/app/login.js'
            //         },
            //         {
            //             name: 'regist',
            //             templateUrl: 'views/regist.html',
            //             controller: 'js/app/regist.js',
            //         }
            //     ]
            // },
            'defaults': 'home' //默认路由
        }
    });
});