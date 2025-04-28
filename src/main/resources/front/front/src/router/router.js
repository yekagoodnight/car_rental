import VueRouter from 'vue-router'
//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Messages from '../pages/messages/list'
import Storeup from '../pages/storeup/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import payList from '../pages/pay'

import yonghuList from '../pages/yonghu/list'
import yonghuDetail from '../pages/yonghu/detail'
import yonghuAdd from '../pages/yonghu/add'
import cheshangList from '../pages/cheshang/list'
import cheshangDetail from '../pages/cheshang/detail'
import cheshangAdd from '../pages/cheshang/add'
import qichechuzuList from '../pages/qichechuzu/list'
import qichechuzuDetail from '../pages/qichechuzu/detail'
import qichechuzuAdd from '../pages/qichechuzu/add'
import qicheleixingList from '../pages/qicheleixing/list'
import qicheleixingDetail from '../pages/qicheleixing/detail'
import qicheleixingAdd from '../pages/qicheleixing/add'
import zulindingdanList from '../pages/zulindingdan/list'
import zulindingdanDetail from '../pages/zulindingdan/detail'
import zulindingdanAdd from '../pages/zulindingdan/add'
import haichejiluList from '../pages/haichejilu/list'
import haichejiluDetail from '../pages/haichejilu/detail'
import haichejiluAdd from '../pages/haichejilu/add'
import dingdanquxiaoList from '../pages/dingdanquxiao/list'
import dingdanquxiaoDetail from '../pages/dingdanquxiao/detail'
import dingdanquxiaoAdd from '../pages/dingdanquxiao/add'
import guzhangshangbaoList from '../pages/guzhangshangbao/list'
import guzhangshangbaoDetail from '../pages/guzhangshangbao/detail'
import guzhangshangbaoAdd from '../pages/guzhangshangbao/add'
import guzhangleixingList from '../pages/guzhangleixing/list'
import guzhangleixingDetail from '../pages/guzhangleixing/detail'
import guzhangleixingAdd from '../pages/guzhangleixing/add'
import shigushangbaoList from '../pages/shigushangbao/list'
import shigushangbaoDetail from '../pages/shigushangbao/detail'
import shigushangbaoAdd from '../pages/shigushangbao/add'
import shiguleixingList from '../pages/shiguleixing/list'
import shiguleixingDetail from '../pages/shiguleixing/detail'
import shiguleixingAdd from '../pages/shiguleixing/add'
import cheliangbaoxianList from '../pages/cheliangbaoxian/list'
import cheliangbaoxianDetail from '../pages/cheliangbaoxian/detail'
import cheliangbaoxianAdd from '../pages/cheliangbaoxian/add'
import baoxianleixingList from '../pages/baoxianleixing/list'
import baoxianleixingDetail from '../pages/baoxianleixing/detail'
import baoxianleixingAdd from '../pages/baoxianleixing/add'
import syslogList from '../pages/syslog/list'
import syslogDetail from '../pages/syslog/detail'
import syslogAdd from '../pages/syslog/add'
import newstypeList from '../pages/newstype/list'
import newstypeDetail from '../pages/newstype/detail'
import newstypeAdd from '../pages/newstype/add'
import sensitivewordsList from '../pages/sensitivewords/list'
import sensitivewordsDetail from '../pages/sensitivewords/detail'
import sensitivewordsAdd from '../pages/sensitivewords/add'
import discussqichechuzuList from '../pages/discussqichechuzu/list'
import discussqichechuzuDetail from '../pages/discussqichechuzu/detail'
import discussqichechuzuAdd from '../pages/discussqichechuzu/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'messages',
					component: Messages
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'yonghu',
					component: yonghuList
				},
				{
					path: 'yonghuDetail',
					component: yonghuDetail
				},
				{
					path: 'yonghuAdd',
					component: yonghuAdd
				},
				{
					path: 'cheshang',
					component: cheshangList
				},
				{
					path: 'cheshangDetail',
					component: cheshangDetail
				},
				{
					path: 'cheshangAdd',
					component: cheshangAdd
				},
				{
					path: 'qichechuzu',
					component: qichechuzuList
				},
				{
					path: 'qichechuzuDetail',
					component: qichechuzuDetail
				},
				{
					path: 'qichechuzuAdd',
					component: qichechuzuAdd
				},
				{
					path: 'qicheleixing',
					component: qicheleixingList
				},
				{
					path: 'qicheleixingDetail',
					component: qicheleixingDetail
				},
				{
					path: 'qicheleixingAdd',
					component: qicheleixingAdd
				},
				{
					path: 'zulindingdan',
					component: zulindingdanList
				},
				{
					path: 'zulindingdanDetail',
					component: zulindingdanDetail
				},
				{
					path: 'zulindingdanAdd',
					component: zulindingdanAdd
				},
				{
					path: 'haichejilu',
					component: haichejiluList
				},
				{
					path: 'haichejiluDetail',
					component: haichejiluDetail
				},
				{
					path: 'haichejiluAdd',
					component: haichejiluAdd
				},
				{
					path: 'dingdanquxiao',
					component: dingdanquxiaoList
				},
				{
					path: 'dingdanquxiaoDetail',
					component: dingdanquxiaoDetail
				},
				{
					path: 'dingdanquxiaoAdd',
					component: dingdanquxiaoAdd
				},
				{
					path: 'guzhangshangbao',
					component: guzhangshangbaoList
				},
				{
					path: 'guzhangshangbaoDetail',
					component: guzhangshangbaoDetail
				},
				{
					path: 'guzhangshangbaoAdd',
					component: guzhangshangbaoAdd
				},
				{
					path: 'guzhangleixing',
					component: guzhangleixingList
				},
				{
					path: 'guzhangleixingDetail',
					component: guzhangleixingDetail
				},
				{
					path: 'guzhangleixingAdd',
					component: guzhangleixingAdd
				},
				{
					path: 'shigushangbao',
					component: shigushangbaoList
				},
				{
					path: 'shigushangbaoDetail',
					component: shigushangbaoDetail
				},
				{
					path: 'shigushangbaoAdd',
					component: shigushangbaoAdd
				},
				{
					path: 'shiguleixing',
					component: shiguleixingList
				},
				{
					path: 'shiguleixingDetail',
					component: shiguleixingDetail
				},
				{
					path: 'shiguleixingAdd',
					component: shiguleixingAdd
				},
				{
					path: 'cheliangbaoxian',
					component: cheliangbaoxianList
				},
				{
					path: 'cheliangbaoxianDetail',
					component: cheliangbaoxianDetail
				},
				{
					path: 'cheliangbaoxianAdd',
					component: cheliangbaoxianAdd
				},
				{
					path: 'baoxianleixing',
					component: baoxianleixingList
				},
				{
					path: 'baoxianleixingDetail',
					component: baoxianleixingDetail
				},
				{
					path: 'baoxianleixingAdd',
					component: baoxianleixingAdd
				},
				{
					path: 'syslog',
					component: syslogList
				},
				{
					path: 'syslogDetail',
					component: syslogDetail
				},
				{
					path: 'syslogAdd',
					component: syslogAdd
				},
				{
					path: 'newstype',
					component: newstypeList
				},
				{
					path: 'newstypeDetail',
					component: newstypeDetail
				},
				{
					path: 'newstypeAdd',
					component: newstypeAdd
				},
				{
					path: 'sensitivewords',
					component: sensitivewordsList
				},
				{
					path: 'sensitivewordsDetail',
					component: sensitivewordsDetail
				},
				{
					path: 'sensitivewordsAdd',
					component: sensitivewordsAdd
				},
				{
					path: 'discussqichechuzu',
					component: discussqichechuzuList
				},
				{
					path: 'discussqichechuzuDetail',
					component: discussqichechuzuDetail
				},
				{
					path: 'discussqichechuzuAdd',
					component: discussqichechuzuAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
