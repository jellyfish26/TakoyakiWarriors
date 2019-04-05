class User {

    constructor() {
        console.log('start');
        this.userInfo = { 'root': '99999999999999999' };

        this.loadUseInfo().then(
            console.log('finish')
        );
    }

    loadUseInfo() {
        return new Promise((resolve, reject) => {
            // DB
            const kintone = require('kintone-nodejs-sdk');

            let APIToken = '教えられないよ！'; // your API Token
            // let basicUserName = 'xxxxx'; // basicAuth user name
            // let basicPassword = 'xxxxx'; // basicAuth password
            let kintoneAuth = new kintone.Auth();
            // kintoneAuth.setBasicAuth(basicUserName, basicPassword);
            kintoneAuth.setApiToken(APIToken);

            let myDomainName = 'devaewdmq.cybozu.com';
            let kintoneConnection = new kintone.Connection(myDomainName, kintoneAuth);

            let kintoneRecord = new kintone.Record(kintoneConnection);

            let appID = 2; // target appID

            var response = {};
            kintoneRecord.getRecords(appID)
                .then((rsp) => {
                    for (let i = 0; i < (rsp['records']).length; i++) {
                        var UserId = rsp['records'][i]['文字列__1行_']['value'];
                        this.userInfo[UserId] = rsp['records'][i]['文字列__1行__0']['value']
                    };
                })
                .catch((err) => {
                    console.log(err);
                });
        })
    }

    getUserPoint(userId) {
        return this.userInfo[userId];
    }

    addUserPoint(userId, point) {
        this.userInfo[userId] = point;
        return true;
    }

    callUserList() {
        console.log(this.userInfo);
    }
}

class Login {

    constructor() {
        this.name = {
            'root': 'x'
        }
    }

    set_name(uid) {
        this.name[uid] = '5';
    }

    del_name(uid) {
        this.name[uid] = '';
    }

    get_name(uid) {
        return this.name[uid];
    }
}

class Takoyaki {

    constructor() {
        console.log('3rd start');
        this.takoinfo = {
            'admin': {
                TP: '1',
                EP: '1',
                HP: '1',
                ATK: '1',
                DEF: '1',
                SPE: '1'
            }
        }
        this.loadTakoInfo().then(
            console.log('3rd-end')
        );
    }

    getTakoyakiInfo(uid) {
        return this.takoinfo[uid];
    }

    loadTakoInfo() {
        return new Promise((resolve, reject) => {
            // DB
            const kintone = require('kintone-nodejs-sdk');

            let APIToken = '教えられないよ！'; // your API Token
            // let basicUserName = 'xxxxx'; // basicAuth user name
            // let basicPassword = 'xxxxx'; // basicAuth password
            let kintoneAuth = new kintone.Auth();
            // kintoneAuth.setBasicAuth(basicUserName, basicPassword);
            kintoneAuth.setApiToken(APIToken);

            let myDomainName = 'devaewdmq.cybozu.com';
            let kintoneConnection = new kintone.Connection(myDomainName, kintoneAuth);

            let kintoneRecord = new kintone.Record(kintoneConnection);

            let appID = 4; // target 

            // var response = {};
            kintoneRecord.getRecords(appID)
                .then((rsp) => {
                    for (let i = 0; i < (rsp['records']).length; i++) {
                        var uid = rsp['records'][i]['文字列__1行__6']['value'];
                        this.takoinfo[String(uid)] = {};
                        // console.log(rsp['records'][i]);
                        var tako = {};
                        tako['TP'] = rsp['records'][i]['文字列__1行_']['value'];
                        tako['EP'] = rsp['records'][i]['文字列__1行__0']['value'];
                        tako['HP'] = rsp['records'][i]['文字列__1行__1']['value'];
                        tako['ATK'] = rsp['records'][i]['文字列__1行__2']['value'];
                        tako['DEF'] = rsp['records'][i]['文字列__1行__3']['value'];
                        tako['SPE'] = rsp['records'][i]['文字列__1行__5']['value'];
                        this.takoinfo[uid] = tako;
                    }
                    console.log(this.takoinfo);
                    return resolve(this.takoinfo);
                    // console.log(response);
                    // return response;


                })
                .catch((err) => {
                    console.log(err);
                });
        })

    }

    // ポイントアップデート
    updatePoint() {
        return new Promise((resolve, reject) => {
            // DB
            const kintone = require('kintone-nodejs-sdk');

            let APIToken = '教えられないよ！'; // your API Token
            // let basicUserName = 'xxxxx'; // basicAuth user name
            // let basicPassword = 'xxxxx'; // basicAuth password
            let kintoneAuth = new kintone.Auth();
            // kintoneAuth.setBasicAuth(basicUserName, basicPassword);
            kintoneAuth.setApiToken(APIToken);

            let myDomainName = 'devaewdmq.cybozu.com';
            let kintoneConnection = new kintone.Connection(myDomainName, kintoneAuth);

            let kintoneRecord = new kintone.Record(kintoneConnection);

            let appID = 4; // target appID

            let recordData = {
                "app": 4,
                "id": 1,
                "record": {
                    "文字列__1行_": {
                        "value": 1010202910281029
                    },
                    "文字列__1行__0": {
                        "value": 2299229
                    }

                }
            };
            kintoneRecord.updateRecordById(appID, 1, recordData)
                .then((rsp) => {
                    console.log(rsp);
                    resolve(rsp);
                    // console.log(response);
                    // return response;
                })
                .catch((err) => {
                    console.log(err.get());
                    resolve(err.get());
                });
        })
    }


    set_statas(id, ep, ap, hp, atk, def, spe) {
        this.takoinfo[id]['ep'] = ep;
        this.takoinfo[id]['ap'] = ap;
        this.takoinfo[id]['hp'] = hp;
        this.takoinfo[id]['atk'] = atk;
        this.takoinfo[id]['def'] = def;
        this.takoinfo[id]['spe'] = spe;
    }

}

class Shop {

    constructor() {
        console.log('2nd start');
        this.shopInfo = { '114514': '天下一たこ焼きや' }
        this.loadShopInfo().then(
            console.log('end')
        );
    }

    get_name() {
        return this.shopInfo;
    }

    loadShopInfo() {
        return new Promise((resolve, reject) => {
            // DB
            const kintone = require('kintone-nodejs-sdk');

            let APIToken = '教えられないよ！'; // your API Token
            // let basicUserName = 'xxxxx'; // basicAuth user name
            // let basicPassword = 'xxxxx'; // basicAuth password
            let kintoneAuth = new kintone.Auth();
            // kintoneAuth.setBasicAuth(basicUserName, basicPassword);
            kintoneAuth.setApiToken(APIToken);

            let myDomainName = 'devaewdmq.cybozu.com';
            let kintoneConnection = new kintone.Connection(myDomainName, kintoneAuth);

            let kintoneRecord = new kintone.Record(kintoneConnection);

            let appID = 3; // target appID

            var shopId, shopName;
            // var response = {};
            kintoneRecord.getRecords(appID)
                .then((rsp) => {
                    for (let i = 0; i < (rsp['records']).length; i++) {
                        var shopId = rsp['records'][i]['文字列__1行_']['value'];
                        var shopName = rsp['records'][i]['文字列__1行__0']['value'];
                        this.shopInfo[shopId] = shopName;
                    }

                    return resolve(this.shopInfo);
                    // console.log(response);
                    // return response;


                })
                .catch((err) => {
                    console.log(err);
                });
        })
    }

}



var fs = require('fs');
var express = require('express');
var app = express();
var http = require('http').Server(app);
const PORT = 8080;
const Request = require('request');
const line = require('@line/bot-sdk');
const axios = require('axios');

user = new User();
shop = new Shop();
tako = new Takoyaki();
login = new Login();

// config
const config = {
    channelSecret: 'あぁ^～心がぴょんぴょんするんじゃぁ^～',
    channelAccessToken: 'あぁ^～心がぴょんぴょんするんじゃぁ^～'
};

// Web Controller

// LINE Webhock
app.post('/', line.middleware(config), (req, res) => {
    console.log(req.body.events);
    Promise
        .all(req.body.events.map(handleEvent))
        .then((result) => res.json(result));
});

// MyPoint
app.get('/getMyPoint', function (req, res) {
    const uid = req['query']['uid'];
    return res.json({
        'id': uid,
        'point': user.userInfo[uid],
        'EP': tako.takoinfo[uid]['EP']
    });
});

// Takoyaki-kun
app.get('/getTakoyaki', function (req, res) {
    const uid = req['query']['uid'];
    const takoo = tako.getTakoyakiInfo(uid);
    if (takoo == undefined) {
        return res.json(
            {
                "TP": "100",
                "EP": "5",
                "HP": "132",
                "ATK": "1",
                "DEF": "4",
                "SPE": "15"
            }
        );
    } else {
        return res.json(takoo);
    }

});

// DBからお店List取得
app.get('/getShopList', function (req, res) {
    // getShopList().then((result) => {
    //     console.log(result);
    //     res.json(result);
    // })
    return res.json(shop.shopInfo);
});

// DBからUserList取得
app.get('/getUserList', function (req, res) {
    // getUserList().then((result) => {
    //     console.log(result);
    //     res.json(user.userInfo);
    // })
    return res.json(user.userInfo);
});

// 画像をジャッジ
app.get('/judge', function (req, res) {
    var ok = judgeImage(req['query']['img']);
    return res.json(
        {
            'success': ok
        }
    );
});


// pointアップデート
app.get('/updateStatus', function (req, res) {
    try {
        id = req['query']['userId'];
        tp = req['query']['tp'];
        ep = req['query']['ep'];
        ap = req['query']['ap'];
        hp = req['query']['hp'];
        atk = req['query']['atk'];
        def = req['query']['def'];
        spe = req['query']['spe'];
        console.log(tako.takoinfo[id]);

        tako.takoinfo[id]['TP'] = tp;
        tako.takoinfo[id]['EP'] = ep;
        tako.takoinfo[id]['AP'] = ap;
        tako.takoinfo[id]['HP'] = hp;
        tako.takoinfo[id]['ATK'] = atk;
        tako.takoinfo[id]['DEF'] = def;
        tako.takoinfo[id]['SPE'] = spe;
        return res.json(tako.takoinfo[id]);
        // updatePoint(1, userId, point);
    } catch (error) {
        console.log('error!!!!!!  ::: ' + error);
        return false;
    }
});

// statusアップデート
app.get('/updatePoint', function (req, res) {
    try {
        userId = req['query']['userId'];
        point = req['query']['point'];
        if (judgeImage()) {
            point += (100 * Math.floor(Math.random() * 5 + 1));
            user.userInfo[userId] = point;
            return res.json({
                'your point': user.getUserPoint(userId)
            });
        } else {
            return false;
        }
        // updatePoint(1, userId, point);
    } catch (error) {
        console.log('error!!!!!!  ::: ' + error);
        return false;
    }
});

// static routing
app.use('/photo', express.static('photo'));


// LINE bot処理
const client = new line.Client(config);

function handleEvent(event) {
    var uid = event.source.userId;
    if (event.type == 'beacon') {
        console.log(event.replyToken);

        login.set_name(uid);
        var shops = shop.get_name(uid);

        return client.replyMessage(event.replyToken, {
            type: 'text',
            text: shops[event.beacon.hwid] + 'に行ったね？'
        });
    }
    else if (event.message.type == 'image') {

        if (login.get_name(uid) == '') {
            return client.replyMessage(event.replyToken, {
                type: 'text',
                text: '不正はなしよ？'
            });
        }
        login.del_name(uid);
        // let imgId = event.message.id;
        const options = {
            url: `https://api.line.me/v2/bot/message/${event.message.id}/content`,
            method: 'get',
            headers: {
                'Authorization': 'Bearer ' + config['channelAccessToken'],
            },
            encoding: null
        };

        Request(options, function (error, response, body) {
            if (!error && response.statusCode == 200) {
                //保存
                fs.writeFileSync('photo/' + event.message.id + '.jpg', new Buffer(body), 'binary');
            } else {
                // @todo handle error
            }
        });

        getNodeVer(uid, event.message.id);
        return client.replyMessage(event.replyToken, {
            type: 'text',
            text: 'お待ちください \n URL: https://example.com/' + event.message.id + '.jpg'
        });


    }
    else if (event.type !== 'message' || event.message.type !== 'text') {
        return Promise.resolve(null);
    }
    console.log(user.userInfo);

    if (event.message.text === 'my point') {
        let point = user.userInfo[uid];
        return client.replyMessage(event.replyToken, {
            type: 'text',
            text: point + 'ポイントです'
        });
    }
    let mes = 'なに？'
    return client.replyMessage(event.replyToken, {
        type: 'text',
        text: mes
    });
}

const getNodeVer = async (userId, msid) => {
    console.log("Goo");
    const res = await axios.get('https://example.com/judge?img=' + msid);

    if (!res) {
        mes = 'たこ焼きじゃないね？'
    }
    else {
        var mes = '10ポイント追加だよ!';
        var point = Number(user.userInfo[userId]);
        point += 10;
        user.userInfo[userId] = point.toString();
    }
    await client.pushMessage(userId, {
        type: 'text',
        text: mes,
    });
}


// 画像ジャッジ
function judgeImage(ms_id) {
    // ほぼ 3/10 で false
    return Math.random() >= 0.3;
}

app.listen(PORT);
console.log(`Server running at ${PORT}`);

