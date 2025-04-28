<template>
	<div class="main-containers">
		<div class="body-containers">
			<div class="top-container">
				<!-- info -->
				<div class="top_title">
					<span @click="goMenu('/index/home')">汽车租赁管理系统</span>
				</div>
			

				<img class="top_avatar1" v-if="headportrait&&Token" :src="headportrait?baseUrl + headportrait:require('@/assets/avator.png')">
				<div class="top_nickname1" v-if="Token">{{username}}</div>
				<el-button v-if="Token" class="btn-service" @click.native="goChat">
					<span class="icon iconfont icon-touxiang09"></span>
						智能AI
				</el-button>
				<el-button v-if="Token && notAdmin" class="btn-user" @click="goMenu('/index/center')">
					<span class="icon iconfont icon-wuliu8"></span>
					个人中心
				</el-button>
				<el-button v-if="!Token" class="btn-login" @click="toLogin">
					<span class="icon iconfont icon-wuliu8"></span>
					登录
				</el-button>
				<el-button v-if="Token" class="btn-register" @click="logout">
					<span class="icon iconfont icon-guanbi19"></span>
					退出
				</el-button>
			</div>


			<div class="menu-preview">
				<el-scrollbar wrap-class="scrollbar-wrapper-horizontal">
					<el-menu class="el-menu-horizontal-demo" :default-active="activeMenu" :unique-opened="true" mode="horizontal" :router="true" @select="handleSelect">
						<div class="userinfo">
						  <el-image v-if="headportrait&&Token" :src="headportrait?baseUrl + headportrait:require('@/assets/avator.png')" fit="cover"></el-image>
						  <div class="nickname">{{username}}</div>
						</div>
						<el-menu-item class="home" index="/index/home" @click.native="goMenu('/index/home')">
							<span class="icon iconfont icon-shouye-zhihui"></span>
							<span>系统首页</span>
						</el-menu-item>
						<el-menu-item class="item" v-for="(menu, index) in menuList" :index="menu.url" :key="index" @click.native="goMenu(menu.url)">
							<i class="icons" :class="iconArr[index]"></i>
							<span>{{menu.name}}</span>
						</el-menu-item>
						<el-menu-item class="service" v-if="Token" @click.native="goChat">
							<span class="icon iconfont icon-touxiang09"></span>
							<span>
								智能AI
							</span>
						</el-menu-item>
						<el-menu-item class="user" index="/index/center" v-if="Token && notAdmin" @click.native="goMenu('/index/center')">
							<span class="icon iconfont icon-shouye-zhihui"></span>
							<span>个人中心</span>
						</el-menu-item>
					</el-menu>
				</el-scrollbar>
			</div>

			<div class="banner-preview" v-if="carouselChange()">
				<el-carousel trigger="click" indicator-position="inside" arrow="always" type="default" direction="horizontal" height="500px" :autoplay="true" :interval="3000" :loop="true">
					<el-carousel-item v-for="item in carouselList" :key="item.id">
						<el-image v-if="preHttp(item.value)" @click="carouselClick(item.url)" :src="item.value" fit="cover"></el-image>
						<el-image v-else @click="carouselClick(item.url)" :src="baseUrl + item.value" fit="cover"></el-image>
						<div class="banner-hidden">
						</div>
					</el-carousel-item>
				</el-carousel>
			</div>
			<router-view id="scrollView"></router-view>
			
			<div class="bottom-preview">
				<div class="footer"><div v-html="bottomContent"></div></div>
			</div>
		</div>
		
		<el-dialog title="智能AI" :visible.sync="chatFormVisible" width="60%" :before-close="chatClose">
			<div class="chat-content" id="chat-content">
				<div v-bind:key="item.id" v-for="item in chatList">
					<div v-if="item.addtime" style="width: 100%;text-align: center;font-size: 10px;color: #666;">{{timeFormat(item.addtime)}}</div>
					<div v-if="item.ask" class="right-content">
						<div style="display: flex;align-items: flex-start;">
							<el-alert v-if="item.type==1" class="text-content" :title="item.ask" :closable="false"
								type="warning"></el-alert>
							<el-image v-else-if="item.type==2" :src="baseUrl + item.ask" style="width: 150px;height: 150px;" fit="cover" :preview-src-list="[baseUrl + item.ask]"></el-image>
							<video v-else-if="item.type==3" :src="baseUrl + item.ask" style="width: 280px;" controls></video>
							<el-button v-else-if="item.type==4" type="primary" size="mini" @click="download(item.ask)">文件预览</el-button>
							<img :src="item.uimage?(baseUrl + item.uimage.split(',')[0]):require('@/assets/avator.png')" style="width: 30px;height: 30px;border-radius: 50%;margin: 0 0 0 5px;" alt="">
						</div>
					</div>
					<div v-else class="left-content">
						<div style="display: flex;align-items: flex-start;">
							<img :src="item.uimage?(baseUrl + item.uimage.split(',')[0]):require('@/assets/AI.png')" style="width: 30px;height: 30px;border-radius: 50%;margin: 0 5px 0 0;" alt="">
							<el-alert v-if="item.type==1" class="text-content" :title="item.reply" :closable="false"
								type="success"></el-alert>
							<el-image v-else-if="item.type==2" :src="baseUrl + item.reply" style="width: 150px;height: 150px;" fit="cover" :preview-src-list="[baseUrl + item.reply]"></el-image>
							<video v-else-if="item.type==3" :src="baseUrl + item.reply" style="width: 280px;" controls></video>
							<el-button v-else-if="item.type==4" type="primary" size="mini" @click="download(item.reply)">文件预览</el-button>
						</div>
					</div>
					<div class="clear-float"></div>
				</div>
			</div>
			<div v-if="aiLoading" v-loading="true" element-loading-background="rgba(255, 255, 255, 0.2)" style="text-align: center">
				AI正在解答您的问题，请稍后...
			</div>
			<div slot="footer" class="dialog-footer">
				<div v-if="askShow"
					style="padding-bottom: 10px;display: flex;align-items: center;justify-content: center;">
					<el-upload class="upload-demo" :action="uploadUrl" :on-success="uploadSuccess" accept=".jpg,.png"
						:show-file-list="false">
						<el-button size="mini" type="success">上传图片</el-button>
					</el-upload>
					<el-upload class="upload-demo" :action="uploadUrl" :on-success="uploadSuccess2" accept=".mp4"
						:show-file-list="false">
						<el-button size="mini" type="success" style="margin: 0 0 0 10px;">上传视频</el-button>
					</el-upload>
					<el-upload class="upload-demo" :action="uploadUrl" :on-success="uploadSuccess3"
						:show-file-list="false">
						<el-button size="mini" type="success" style="margin: 0 0 0 10px;">上传文件</el-button>
					</el-upload>
					<el-button size="mini" type="primary" style="margin: 0 0 0 10px;" @click="askChange">
						转{{askType==1?'人工服务':'智能回复'}}</el-button>
				</div>
				<div style="width: 100%;display: flex;align-items: center;justify-content: space-between;">
					<img style="width: 30px;cursor: pointer;" @click="askShow = !askShow" src="../assets/jiahao.png">
					<el-input @keydown.enter.native="addChat(null)" v-model="form.ask" placeholder="请输入内容" style="width: calc(100% - 110px);">
					</el-input>
					<el-button type="primary" @click="addChat(null)">发送</el-button>
					<div style="position: relative;" v-if="askType==2">
						<span @click="showEmoji=!showEmoji" class="icon iconfont icon-gerenzhongxin-zhihui" style="font-size: 30px;color: #666;cursor: pointer;"></span>
						<picker
							:include="['people', 'Smileys']"
							:showSearch="false"
							:showPreview="false"
							:showCategories="false"
							@select="addEmoji"
							v-if="showEmoji"
							:backgroundImageFn="((set,sheetSize)=>{
								return require('@/assets/32.png')
							})"
							style="position: absolute;bottom: 40px;left: -100px;"
						/>
					</div>
				</div>
			</div>
		</el-dialog>
	</div>
</template>

<script>
	import Vue from 'vue'
	import Swiper from "swiper";
	import axios from 'axios'
	import { Picker } from "emoji-mart-vue";
	import timeMethod from '@/common/timeMethod'
	import {
		WebsocketMixin
	} from '@/mixins/WebsocketMixin'
export default {
	components:{
		Picker
	},
	mixins: [WebsocketMixin],
	data() {
		return {
			activeIndex: '0',
			baseUrl: '',
			carouselList: [],
			carouselForm: {
				inHome: true,
				inOther: true,
			},
			menuList: [],
			askType: 1, //1为智能回复 2为人工服务
			chatFormVisible: false,
			chatList: [],
			headers: {
				Token: localStorage.getItem('frontToken')
			},
			uploadUrl: this.$config.baseUrl + 'file/upload',
			askShow: false,
			aiLoading: false,
			showEmoji: false,
			form: {
				ask: '',
			},
			headportrait: localStorage.getItem('frontHeadportrait')?localStorage.getItem('frontHeadportrait'):'',
			Token: localStorage.getItem('frontToken'),
			username: localStorage.getItem('username'),
			notAdmin: localStorage.getItem('frontSessionTable')!='"users"',
			iconArr: [
				'el-icon-star-off',
				'el-icon-goods',
				'el-icon-warning',
				'el-icon-question',
				'el-icon-info',
				'el-icon-help',
				'el-icon-picture-outline-round',
				'el-icon-camera-solid',
				'el-icon-video-camera-solid',
				'el-icon-video-camera',
				'el-icon-bell',
				'el-icon-s-cooperation',
				'el-icon-s-order',
				'el-icon-s-platform',
				'el-icon-s-operation',
				'el-icon-s-promotion',
				'el-icon-s-release',
				'el-icon-s-ticket',
				'el-icon-s-management',
				'el-icon-s-open',
				'el-icon-s-shop',
				'el-icon-s-marketing',
				'el-icon-s-flag',
				'el-icon-s-comment',
				'el-icon-s-finance',
				'el-icon-s-claim',
				'el-icon-s-opportunity',
				'el-icon-s-data',
				'el-icon-s-check'
			],
			bottomContent: '',
			showType4: -1,
		}
	},
	async created() {
		this.baseUrl = this.$config.baseUrl;
		this.menuList = this.$config.indexNav;
		this.getCarousel();
		if(localStorage.getItem('frontToken') && localStorage.getItem('frontToken')!=null) {
			this.getSession()
		}
	},
	mounted() {
		this.activeIndex = localStorage.getItem('keyPath') || '0';



	},
	computed: {
		activeMenu() {
			const route = this.$route
			const {
				meta,
				path
			} = route
			// if st path, the sidebar will highlight the path you sete
			if (meta.activeMenu) {
				return meta.activeMenu
			}
			return path
		},
	},
	watch: {
		$route(newValue) {
			let that = this
			let url = window.location.href
			let arr = url.split('#')
			for (let x in this.menuList) {
				if (newValue.path == this.menuList[x].url) {
					this.activeIndex = x
				}
			}
			this.Token = localStorage.getItem('frontToken')
			if(arr[1]!='/index/home'){
				var element = document.getElementById('scrollView');
				var distance = element.offsetTop;
				window.scrollTo( 0, distance )
			}else{
				window.scrollTo( 0, 0 )
			}
		},
		headportrait(){
			this.$forceUpdate()
		},
	},
	methods: {
		preHttp(str) {
			return str && str.substr(0,4)=='http';
		},

		async getSession() {
			await this.$http.get(`${localStorage.getItem('UserTableName')}/session`, {emulateJSON: true}).then(async res => {
				if (res.data.code == 0) {
					localStorage.setItem('sessionForm',JSON.stringify(res.data.data))
					localStorage.setItem('frontUserid', res.data.data.id);
					if(res.data.data.vip) {
						localStorage.setItem('vip', res.data.data.vip);
					}
					if(res.data.data.touxiang) {
						this.headportrait = res.data.data.touxiang
						localStorage.setItem('frontHeadportrait', res.data.data.touxiang);
					} else if(res.data.data.headportrait) {
						this.headportrait = res.data.data.headportrait
						localStorage.setItem('frontHeadportrait', res.data.data.headportrait);
					}
				}
			});
		},
		handleSelect(keyPath) {
			if (keyPath) {
				localStorage.setItem('keyPath', keyPath)
			}
		},
		toLogin() {
		  this.$router.push('/login');
		},
		logout() {
			localStorage.clear();
			Vue.http.headers.common['Token'] = "";
			this.$router.push('/index/home');
			this.activeIndex = '0'
			localStorage.setItem('keyPath', this.activeIndex)
			this.Token = ''
			this.$forceUpdate()
			this.$message({
				message: '登出成功',
				type: 'success',
				duration: 1000,
			});
		},
		getCarousel() {
			this.$http.get('config/list', {params: { page: 1, limit: 3 }}).then(res => {
				if (res.data.code == 0) {
					this.carouselList = res.data.data.list;
				}
			});
		},
		// 轮播图跳转
		carouselClick(url) {
			if (url) {
				if (url.indexOf('https') != -1) {
					window.open(url)
				} else {
					this.$router.push(url)
				}
			}
		},
		carouselChange(){
			let url = window.location.href
			let arr = url.split('#')
			return (this.carouselForm.inHome&&arr[1]=='/index/home')||(this.carouselForm.inOther&&arr[1]!='/index/home')
		},
		goBackend() {
			localStorage.setItem('Token', localStorage.getItem('frontToken'));
			localStorage.setItem('role', localStorage.getItem('frontRole'));
			localStorage.setItem('sessionTable', localStorage.getItem('frontSessionTable'));
			localStorage.setItem('headportrait', localStorage.getItem('frontHeadportrait'));
			localStorage.setItem('userid', localStorage.getItem('frontUserid'));
			window.location.href = `${this.$config.baseUrl}admin/dist/index.html`
			
		},
		formatMessages(messages) {
			let lastTime = null;
			messages.forEach((message, index) => {
				const currentTime = new Date(message.addtime).getTime();
				if (lastTime !== null) {
					const timeDiff = (currentTime - lastTime) / 1000 / 60; // 转换为分钟
					if (timeDiff < 3) {
						message.addtime = ''; // 如果小于3分钟，不显示时间
					}
				}
				lastTime = currentTime;
			});
			return messages;
		},
		timeFormat(time) {
			const Time = timeMethod.getTime(time).split("T");
			//当前消息日期属于周
			const week = timeMethod.getDateToWeek(time);
			//当前日期0时
			const nti = timeMethod.setTimeZero(timeMethod.getNowTime());
			//消息日期当天0时
			const mnti = timeMethod.setTimeZero(timeMethod.getTime(time));
			//计算日期差值
			const diffDate = timeMethod.calculateTime(nti, mnti);
			//本周一日期0时 （后面+1是去除当天时间）
			const fwnti = timeMethod.setTimeZero(timeMethod.countDateStr(-timeMethod.getDateToWeek(timeMethod
				.getNowTime()).weekID + 1));
			//计算周日期差值
			const diffWeek = timeMethod.calculateTime(mnti, fwnti);
		
			if (diffDate === 0) { //消息发送日期减去当天日期如果等于0则是当天时间
				return Time[1].slice(0, 5);
			} else if (diffDate < 172800000) { //当前日期减去消息发送日期小于2天（172800000ms）则是昨天-  一天最大差值前天凌晨00:00:00到今天晚上23:59:59
				return "昨天 " + Time[1].slice(0, 5);
			} else if (diffWeek >= 0) { //消息日期减去本周一日期大于0则是本周
				return week.weekName;
			} else { //其他时间则是日期
				return Time[0].slice(5, 10);
			}
		},
		addEmoji(e) {
			this.form.ask += e.native;
			this.showEmoji = false
		},
		getChatList() {
			this.$http.get('chat/list', {params: { userid: Number(localStorage.getItem('frontUserid')), sort: 'addtime', order: 'asc',limit: 1000 }}).then(res => {
				if (res.data.code == 0) {
					this.chatList = this.formatMessages(res.data.data.list);
					let div = document.getElementsByClassName('chat-content')[0]
					setTimeout(() => {
						if (div){
							div.scrollTop = div.scrollHeight
						}
					}, 0)
				}
			});
		},
		addChat(ask=null,type=1) {
			let params = JSON.parse(JSON.stringify(this.form))
			if(params.ask==''&&ask==null){
				this.$message.error('内容不能为空')
				return false
			}
			if(ask){
				params.ask = ask
			}
			params.type = type
			params.uimage = localStorage.getItem('frontHeadportrait')
			params.uname = localStorage.getItem('username')
			params.userid = Number(localStorage.getItem('frontUserid'))
			this.$http.post('chat/add', params).then(res => {
				if (res.data.code == 0) {
					this.getChatList();
					if (this.askType == 1 && ask == null) {
						let ask2 = JSON.parse(JSON.stringify(this.form.ask))
						this.getChathelper(ask2)
					}
					if(this.askType==2){
						this.websocketSend(ask?ask:params.ask)
					}
					this.form.ask = '';
				}
			});
		},
		chatClose() {
			if(this.askType==2){
				this.websocketOnclose();
			}
			this.chatFormVisible = false;
		},
		websocketOnmessage:function(e) {
			this.getChatList()
		},
		goChat() {
			if(!localStorage.getItem('frontToken')) {
				this.toLogin();
				return;
			}
			this.askType = 1
			this.saveChathelper('主人，我是您的智能助手小搏，请问有什么可以帮您！');
			this.getChatList();
			this.chatFormVisible = true;
		},
		uploadSuccess(res) {
			if (res.code == 0) {
				this.askShow = !this.askShow;
				this.addChat('upload/' + res.file,2)
			}
		},
		uploadSuccess2(res) {
			if (res.code == 0) {
				this.askShow = !this.askShow;
				this.addChat('upload/' + res.file,3)
			}
		},
		uploadSuccess3(res) {
			if (res.code == 0) {
				this.askShow = !this.askShow;
				this.addChat('upload/' + res.file,4)
			}
		},
		download(url){
			if(!url){
				return false
			}
			window.open((location.href.split(this.$config.name).length>1 ? location.href.split(this.$config.name)[0] + this.$config.name + '/' + url :this.$config.baseUrl + url))
		},
		getChathelper(ask) {
			this.aiLoading = true
			let div = document.getElementsByClassName('chat-content')[0]
			console.log(div)
			setTimeout(() => {
				if (div){
					div.scrollTop = div.scrollHeight
				}
			}, 100)
			this.$http.post('baidu/askai', {
				ask: `${ask}`,
			}).then(res => {
				if (res.data.code == 0) {
					this.aiLoading = false
					this.saveChathelper(res.data.data);
				}else {
					this.aiLoading = false
					this.saveChathelper('主人，AI还不够聪明，无法理解您的意思！')
				}
			});
		},
		saveChathelper(reply) {
			this.$http.post('chat/save', {
				reply: reply,
				userid: Number(localStorage.getItem('frontUserid')),
				type: 1
			}).then(res => {
				if (res.data.code == 0) {
					this.form.ask = '';
					this.getChatList();
				}
			});
		},
		askChange() {
			this.askShow = !this.askShow;
			this.askType = this.askType == 1 ? 2 : 1
			if(this.askType==1) {
				this.saveChathelper('主人，我是您的智能助手小搏，请问有什么可以帮您！');
				this.websocketOnclose();
			} else if(this.askType==2){
				this.saveChathelper('您好，在线客服很高兴为您服务！');
				this.initWebSocket(1)
			}
		},
		menuShowClick4(index){
			this.showType4 = index
		},
		goMenu(path) {
			this.$router.push(path);
		},
	}
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.main-containers {
		.body-containers {
			padding: 64px 0 0;
			margin: 0;
			background: #F9F9F9;
			min-height: 100vh;
			position: relative;
			.top-container {
				box-shadow: 0 0px 0px rgba(64, 158, 255, .3);
				padding: 0 20px;
				z-index: 1002;
				top: 0;
				left: 0;
				background: url(http://codegen.caihongy.cn/20250124/f1c8efe0b6644ac4b60c563369a7ed5d.png) center center/100% 100%;
				display: flex;
				width: 100%;
				justify-content: flex-end;
				align-items: center;
				position: absolute;
				height: 64px;
				.top_title {
					left: 20px;
					bottom: -60px;
					display: block;
					position: absolute;
					span {
						color: #1B3E90;
						font-weight: bold;
						font-size: 20px;
						line-height: 60px;
						float: left;
					}
				}
				.top_avatar1 {
					border-radius: 50%;
					margin: 0 0;
					width: 40px;
					height: 40px;
					order: 4;
				}
				.top_nickname1 {
					padding: 0 12px;
					color: #fff;
					font-size: 14px;
					line-height: 32px;
					height: 32px;
					order: 5;
				}
				.btn-service {
					border: 0;
					padding: 0 8px;
					margin: 0 10px;
					color: #333;
					background: #efefef;
					display: none;
					width: auto;
					font-size: 14px;
					line-height: 32px;
					height: 32px;
					.icon {
						color: inherit;
						font-size: 14px;
					}
				}
				.btn-service:hover {
					color: #fff;
					background: red;
				}
				.btn-user {
					border: 0;
					padding: 0 8px;
					margin: 0 10px;
					color: #333;
					background: #efefef;
					display: none;
					width: auto;
					font-size: 14px;
					line-height: 32px;
					height: 32px;
					.icon {
						color: inherit;
						font-size: 14px;
					}
				}
				.btn-user:hover {
					color: #fff;
					background: red;
				}
				.btn-login {
					border: 0;
					padding: 0 8px;
					margin: 0 10px;
					color: #fff;
					background: none;
					width: auto;
					font-size: 14px;
					line-height: 32px;
					height: 32px;
					order: 7;
					.icon {
						color: inherit;
						display: none;
						font-size: 14px;
					}
				}
				.btn-login:hover {
					opacity: 0.7;
				}
				.btn-register {
					border: 0;
					padding: 0 8px;
					margin: 0 10px;
					color: #fff;
					background: none;
					width: auto;
					font-size: 14px;
					line-height: 32px;
					height: 32px;
					order: 7;
					.icon {
						color: inherit;
						font-size: 14px;
					}
				}
				.btn-register:hover {
					opacity: 0.7;
				}
			}
			.menu-preview {
				.el-scrollbar {
					height: 100%;
			  
					& /deep/ .scrollbar-wrapper-vertical {
						overflow-x: hidden;
					}
			  
					& /deep/ .scrollbar-wrapper-horizontal {
						overflow-y: hidden;
			  
						.el-scrollbar__view {
							white-space: nowrap;
						}
					}
				}
				padding: 20px 20px 0;
				background: #ffffff;
				width: 100%;
				border-color: #efefef;
				border-width: 0;
				border-style: solid;
				height: auto;
				.el-menu-horizontal-demo {
					border: 0;
					padding: 0;
					margin: 0 0 0 auto;
					background: #FFF;
					display: flex;
					width: 70%;
					position: relative;
					list-style: none;
					.userinfo {
						padding: 6px 10px 0;
						display: none;
						width: 84px;
						height: auto;
						.el-image {
							border-radius: 20px;
							object-fit: cover;
							display: block;
							width: 100%;
							height: 32px;
						}
						.nickname {
							color: #333;
							font-size: 12px;
							line-height: 1.5;
							text-align: center;
						}
					}
					// 首页
					.el-menu-item.home {
						cursor: pointer;
						padding: 0;
						margin: 0 10px;
						color: #333;
						white-space: nowrap;
						font-weight: bold;
						display: flex;
						font-size: 15px;
						border-color: transparent;
						line-height: 40px;
						background: #fff;
						border-width: 0 0 5px;
						align-items: center;
						position: relative;
						border-style: solid;
						list-style: none;
						height: 40px;
					}
					.el-menu-item.home .icon {
						padding: 0 10px;
						margin: 0;
						color: inherit;
						display: none;
						width: 14px;
						font-size: 14px;
						line-height: 60px;
						height: 60px;
					}
					
					.el-menu-item.home:hover {
						color: #1B3E90;
						background: none;
						border-color: #1B3E90;
					}
					
					.el-menu-item.home.is-active {
						color: #1B3E90;
						background: none;
						border-color: #1B3E90;
					}
					// 其他盒子
					.el-menu-item.item {
						cursor: pointer;
						padding: 0;
						margin: 0 10px;
						color: #333;
						white-space: nowrap;
						font-weight: bold;
						display: flex;
						font-size: 15px;
						border-color: transparent;
						line-height: 40px;
						background: #fff;
						border-width: 0 0 5px;
						align-items: center;
						position: relative;
						border-style: solid;
						list-style: none;
						height: 40px;
					}
					.el-menu-item.item .icons {
						padding: 0 10px;
						margin: 0;
						color: inherit;
						display: none;
						width: 14px;
						font-size: 14px;
						line-height: 60px;
						height: 60px;
					}
					
					.el-menu-item.item:hover {
						color: #1B3E90;
						background: none;
						border-color: #1B3E90;
					}
					
					.el-menu-item.item.is-active {
						color: #1B3E90;
						background: none;
						border-color: #1B3E90;
					}
					.el-menu-item.service {
						cursor: pointer;
						padding: 0;
						margin: 0 10px;
						color: #333;
						white-space: nowrap;
						font-weight: bold;
						display: flex;
						font-size: 15px;
						border-color: transparent;
						line-height: 40px;
						background: #fff;
						border-width: 0 0 5px;
						align-items: center;
						position: relative;
						border-style: solid;
						list-style: none;
						height: 40px;
						order: 4;
					}
					.el-menu-item.service .icon {
						padding: 0 10px;
						margin: 0;
						color: inherit;
						display: none;
						width: 14px;
						font-size: 14px;
						line-height: 60px;
						height: 60px;
					}
					
					.el-menu-item.service:hover {
						color: #1B3E90;
						background: none;
						border-color: #1B3E90;
					}
					
					.el-menu-item.service.is-active {
						color: #1B3E90;
						background: none;
						border-color: #1B3E90;
					}
					.el-menu-item.user {
						cursor: pointer;
						padding: 0;
						margin: 0 10px;
						color: #333;
						white-space: nowrap;
						font-weight: bold;
						display: flex;
						font-size: 15px;
						border-color: transparent;
						line-height: 40px;
						background: #fff;
						border-width: 0 0 5px;
						align-items: center;
						position: relative;
						border-style: solid;
						list-style: none;
						height: 40px;
						order: 5;
					}
					.el-menu-item.user .icon {
						padding: 0 10px;
						margin: 0;
						color: inherit;
						display: none;
						width: 14px;
						font-size: 14px;
						line-height: 60px;
						height: 60px;
					}
					
					.el-menu-item.user:hover {
						color: #1B3E90;
						background: none;
						border-color: #1B3E90;
					}
					
					.el-menu-item.user.is-active {
						color: #1B3E90;
						background: none;
						border-color: #1B3E90;
					}
				}
			}
			.banner-preview {
				width: 100%;
				height: auto;
				.el-carousel {
					margin: 0 auto;
					width: 100%;
					/deep/ .el-carousel__item {
						border-radius: 0;
						width: 100%;
						height: 100%;
						@keyframes wave1 {from { left: -236px } to { left: -1233px }}
						@keyframes wave2 {from { left: 0 } to { left: -1009px }}
						.el-image {
							object-fit: cover;
							width: 100%;
							height: 100%;
						}
					}
					/deep/ .el-carousel__container .el-carousel__arrow--left {
						width: 36px;
						font-size: 12px;
						height: 36px;
					}
					/deep/ .el-carousel__container .el-carousel__arrow--left:hover {
						background: #1B3E90;
					}
					/deep/ .el-carousel__container .el-carousel__arrow--right {
						width: 36px;
						font-size: 12px;
						height: 36px;
					}
					/deep/ .el-carousel__container .el-carousel__arrow--right:hover {
						background: #1B3E90;
					}
					/deep/ .el-carousel__indicators {
						padding: 0;
						margin: 0;
						z-index: 2;
						position: absolute;
						list-style: none;
						li {
							padding: 0;
							margin: 0 4px;
							background: #1B3E90;
							display: inline-block;
							width: 12px;
							opacity: 0.4;
							transition: 0.3s;
							height: 12px;
						}
						li:hover {
							padding: 0;
							margin: 0 4px;
							background: #1B3E90;
							display: inline-block;
							width: 24px;
							opacity: 0.7;
							height: 12px;
						}
						li.is-active {
							padding: 0;
							margin: 0 4px;
							background: #1B3E90;
							display: inline-block;
							width: 24px;
							opacity: 1;
							height: 12px;
						}
					}
					/deep/ .el-carousel__indicator button {
						width: 0;
						height: 0;
						display: none;
					}
				}
			}
			.bottom-preview {
				width: 100%;
				height: auto;
				.footer {
					padding: 20px;
					overflow: hidden;
					background: #1B3E90;
					width: 100%;
					height: auto;
				}
			}
		}
	}
	.chat-content {
		padding-bottom: 20px;
		width: 100%;
		margin-bottom: 10px;
		max-height: 600px;
		height: 600px;
		overflow-y: scroll;
		border: 1px solid #eeeeee;
		background: #fff;

		.left-content {
			float: left;
			margin-bottom: 10px;
			padding: 10px;
			max-width: 80%;
		}

		.right-content {
			float: right;
			margin-bottom: 10px;
			padding: 10px;
			max-width: 80%;
		}
	}

	.clear-float {
		clear: both;
	}
	.emoji-mart[data-v-7bc71df8] {
		font-family: -apple-system, BlinkMacSystemFont, "Helvetica Neue", sans-serif;
		display: -ms-flexbox;
		display: flex;
		-ms-flex-direction: column;
		flex-direction: column;
		height: 420px;
		color: #ffffff !important;
		border: 1px solid #d9d9d9;
		border-radius: 5px;
		background: #fff;
	}
</style>
