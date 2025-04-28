import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
	import news from '@/views/modules/news/list'
	import dingdanquxiao from '@/views/modules/dingdanquxiao/list'
	import guzhangleixing from '@/views/modules/guzhangleixing/list'
	import discussqichechuzu from '@/views/modules/discussqichechuzu/list'
	import cheliangbaoxian from '@/views/modules/cheliangbaoxian/list'
	import zulindingdan from '@/views/modules/zulindingdan/list'
	import syslog from '@/views/modules/syslog/list'
	import qicheleixing from '@/views/modules/qicheleixing/list'
	import qichechuzu from '@/views/modules/qichechuzu/list'
	import yonghu from '@/views/modules/yonghu/list'
	import shigushangbao from '@/views/modules/shigushangbao/list'
	import chat from '@/views/modules/chat/list'
	import haichejilu from '@/views/modules/haichejilu/list'
	import shiguleixing from '@/views/modules/shiguleixing/list'
	import messages from '@/views/modules/messages/list'
	import guzhangshangbao from '@/views/modules/guzhangshangbao/list'
	import baoxianleixing from '@/views/modules/baoxianleixing/list'
	import config from '@/views/modules/config/list'
	import cheshang from '@/views/modules/cheshang/list'
	import newstype from '@/views/modules/newstype/list'


//2.配置路由   注意：名字
export const routes = [{
	path: '/',
	name: '系统首页',
	component: Index,
	children: [{
		// 这里不设置值，是把main作为默认页面
		path: '/',
		name: '系统首页',
		component: Home,
		meta: {icon:'', title:'center', affix: true}
	}, {
		path: '/updatePassword',
		name: '修改密码',
		component: UpdatePassword,
		meta: {icon:'', title:'updatePassword'}
	}, {
		path: '/pay',
		name: '支付',
		component: pay,
		meta: {icon:'', title:'pay'}
	}, {
		path: '/center',
		name: '个人信息',
		component: center,
		meta: {icon:'', title:'center'}
	}
	,{
		path: '/news',
		name: '新闻资讯',
		component: news
	}
	,{
		path: '/dingdanquxiao',
		name: '订单取消',
		component: dingdanquxiao
	}
	,{
		path: '/guzhangleixing',
		name: '故障类型',
		component: guzhangleixing
	}
	,{
		path: '/discussqichechuzu',
		name: '汽车租赁评论',
		component: discussqichechuzu
	}
	,{
		path: '/cheliangbaoxian',
		name: '车辆保险',
		component: cheliangbaoxian
	}
	,{
		path: '/zulindingdan',
		name: '租赁订单',
		component: zulindingdan
	}
	,{
		path: '/syslog',
		name: '系统日志',
		component: syslog
	}
	,{
		path: '/qicheleixing',
		name: '汽车类型',
		component: qicheleixing
	}
	,{
		path: '/qichechuzu',
		name: '汽车租赁',
		component: qichechuzu
	}
	,{
		path: '/yonghu',
		name: '用户',
		component: yonghu
	}
	,{
		path: '/shigushangbao',
		name: '事故上报',
		component: shigushangbao
	}
	,{
		path: '/chat',
		name: '智能AI',
		component: chat
	}
	,{
		path: '/haichejilu',
		name: '还车记录',
		component: haichejilu
	}
	,{
		path: '/shiguleixing',
		name: '事故类型',
		component: shiguleixing
	}
	,{
		path: '/messages',
		name: '留言板管理',
		component: messages
	}
	,{
		path: '/guzhangshangbao',
		name: '故障上报',
		component: guzhangshangbao
	}
	,{
		path: '/baoxianleixing',
		name: '保险类型',
		component: baoxianleixing
	}
	,{
		path: '/config',
		name: '轮播图管理',
		component: config
	}
	,{
		path: '/cheshang',
		name: '车商',
		component: cheshang
	}
	,{
		path: '/newstype',
		name: '新闻资讯分类',
		component: newstype
	}
	]
	},
	{
		path: '/login',
		name: 'login',
		component: Login,
		meta: {icon:'', title:'login'}
	},
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {icon:'', title:'register'}
	},
	{
		path: '*',
		component: NotFound
	}
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
	mode: 'hash',
	/*hash模式改为history*/
	routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
export default router;
