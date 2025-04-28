<template>
	<div class="news-detail-box">
		<div class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="news-detail-view">
			<div class="title-box">
				<div>新闻资讯</div>
			</div>
			<div class="news-detail">
				<div class="detail-title">{{detail.title}}</div>
				<div class="infoBox">
					<div class="time_item">
						<span class="icon iconfont icon-shijian21"></span>
						<span class="label">发布时间：</span>
						<span class="text">{{detail.addtime.split(' ')[0]}}</span>
					</div>
					<div class="publisher_item">
						<span class="icon iconfont icon-geren16"></span>
						<span class="label">发布人：</span>
						<span class="text">{{detail.name}}</span>
					</div>
					<div class="like_item">
						<span class="icon iconfont icon-zan10"></span>
						<span class="label">点赞数：</span>
						<span class="text">{{detail.thumbsupnum}}</span>
					</div>
					<div class="collect_item">
						<span class="icon iconfont icon-shoucang10"></span>
						<span class="label">收藏量：</span>
						<span class="text">{{detail.storeupnum}}</span>
					</div>
					<div class="view_item">
						<span class="icon iconfont icon-chakan9"></span>
						<span class="label">点击量：</span>
						<span class="text">{{detail.clicknum}}</span>
					</div>
				</div>
				<div class="operate">
					<div class="zan-btn" @click="zanClick" >
						<span class="icon iconfont" :class="zanType?'icon-guanzhu-zhihui':'icon-guanzhu-zhihui'" ></span>
						<span class="text" >{{zanType?'取消点赞':'点赞'}}</span>
					</div>
					<div class="collect-btn" @click="collectClick" >
						<span class="icon iconfont" :class="collectType?'icon-shoucang10':'icon-shoucang10'" ></span>
						<span class="text" >{{collectType?'取消收藏':'收藏'}}</span>
					</div>
				</div>
				
				<div class="content-detail ql-snow ql-editor" v-html="detail.content"></div>
			</div>
			<!-- option -->
			<div class="option-box">
				<div class="prev-btn" @click="prepDetailClick">
					<span class="text">上一篇：prev</span>
					<span class="icon iconfont icon-jiantou08"></span>
				</div>
				<div class="next-btn" @click="nextDetailClick">
					<span class="text">下一篇：next</span>
					<span class="icon iconfont icon-jiantou09"></span>
				</div>
			</div>

			<!-- 热门文章 -->
			<div class="hot">
				<div class="hot-title">热门文章</div>
				<div class="hot-list">
					<div class="hot-item" v-for="item in hotList" :key="item.id" @click="toDetail(item.id)">
						<img :src="baseUrl + item.picture" alt="">
						<div class="hot-name">{{ item.title }}</div>
						<div class="hot-time">{{item.addtime}}</div>
					</div>
				</div>
			</div>
			<!-- 推荐文章 -->
			<div class="news">
				<div class="news-title">最新动态</div>
				<div class="news-list">
					<div class="news-item" v-for="item in recommendList" :key="item.id" @click="toDetail(item.id)">
						<img :src="baseUrl + item.picture" alt="">
						<div class="news-name">{{ item.title }}</div>
						<div class="news-time">{{item.addtime}}</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		//数据集合
		data() {
			return {
				id: 0,
				detail: {},
				zanType: false,
				zanForm: {},
				collectType:false,
				collectForm: {},
				baseUrl:'',
				hotList: [],
				recommendList: [],
				categoryList: [],
				currentIndex: 0,
				allList: [],
				sensitiveWordsArr: [],
				storeupType: false,
			}
		},
		created() {
			if(this.$route.query.storeupType&&this.$route.query.storeupType!=0) {
				this.storeupType = true
			}
			this.id = this.$route.query.id
			this.baseUrl = this.$config.baseUrl;
			this.getCategoryList()
			this.getDetail()
			this.getNewsList()
			this.getHotList()
			this.getRecommendList()
			this.getSensitiveWords()
		},
		watch:{
			$route(newValue) {
				this.id = this.$route.query.id
			    this.getDetail()
			}
		},
		methods: {
			getSensitiveWords(){
				this.$http.get('sensitivewords/detail/1').then(rs=>{
					this.sensitiveWordsArr = rs.data.data.content.split(',')
				})
			},
			backClick() {
				if(this.storeupType){
					history.back()
				}else {
					this.$router.push({path: '/index/news'});
				}
			},
			getNewsList() {
				let params = {page:1, limit: 100,sort:'addtime',order:'desc'};
				this.$http.get('news/list', {params: params}).then(res => {
					if (res.data.code == 0) {
						for(let x in res.data.data.list){
							if(res.data.data.list[x].id == this.id){
								this.currentIndex = Number(x)
								break
							}
						}
						this.allList = res.data.data.list
					}
				});
			},
			// 上一篇
			prepDetailClick(){
				if(this.currentIndex == 0){
					this.$message.error('已经是第一篇了')
					return false
				}
				this.currentIndex--
				this.$router.push({path: '/index/newsDetail', query: {id: this.allList[this.currentIndex].id}});
			},
			// 下一篇
			nextDetailClick(){
				if(this.currentIndex == this.allList.length - 1){
					this.$message.error('已经是最后一篇了')
					return false
				}
				this.currentIndex++
				this.$router.push({path: '/index/newsDetail', query: {id: this.allList[this.currentIndex].id}});
			},
			getCategoryList(){
				this.$http.get('newstype/list', {}).then(res => {
					if (res.data.code == 0) {
						this.categoryList = res.data.data;
					}
				});
			},
			// 获取热门列表
			getHotList(){
				let params = {page:1, limit: 4,sort:'addtime',order:'desc'};
				this.$http.get('news/autoSort', {params: params}).then(res => {
					if (res.data.code == 0) {
						this.hotList = res.data.data.list;
					}
				});
			},
			// 获取推荐列表
			getRecommendList(){
				let url = 'news/autoSort'
				if(localStorage.getItem('frontToken')){
					url = 'news/autoSort2'
				}
				let params = {page:1, limit: 4,sort:'addtime',order:'desc'};
				this.$http.get(url, {params: params}).then(res => {
					if (res.data.code == 0) {
						this.recommendList = res.data.data.list;
					}
				});
			},
			// 跳转详情
			toDetail(id){
				this.$router.push({path: '/index/newsDetail', query: {id: id}});
			},
			getDetail(){
				this.$http.get(`news/detail/${this.id}`,{}).then(res=>{
					if(res.data&&res.data.code==0){
						this.detail = res.data.data
						window.scrollTo(0, 100)
						this.getZan()
						this.getCollect()
					}
				})
			},
			getZan() {
				this.$http.get('storeup/list', {
					params: {
						page: 1,
						limit: 1,
						type: '21',
						userid: Number(localStorage.getItem('frontUserid')),
						tablename: 'news',
						refid: this.id
					}
				}).then(res=>{
					if(res.data&&res.data.code==0){
						if(res.data.data.list.length){
							this.zanType = true
							this.zanForm = res.data.data.list[0]
						}else{
							this.zanType = false
						}
					}
				})
			},
			getCollect(){
				this.$http.get('storeup/list', {
					params: {
						page: 1,
						limit: 1,
						type: '1',
						userid: Number(localStorage.getItem('frontUserid')),
						tablename: 'news',
						refid: this.id
					}
				}).then(res=>{
					if(res.data&&res.data.code==0){
						if(res.data.data.list.length){
							this.collectType = true
							this.collectForm = res.data.data.list[0]
						}else{
							this.collectType = false
						}
					}
				})
			},
			zanClick() {
				if(this.zanType){
					this.$http.post('storeup/delete', [this.zanForm.id]).then(res => {
						if (res.data && res.data.code == 0) {
							this.$message.success('取消成功')
							this.detail.thumbsupnum--
							this.$http.post('news/update',this.detail).then(obj=>{})
							this.getZan()
						}
					})
				}else{
					let data = {
						name: this.detail.title,
						picture: this.detail.picture,
						refid: this.detail.id,
						type: '21',
						tablename: 'news',
						userid: Number(localStorage.getItem('frontUserid')),
					}
					this.$http.post('storeup/add', data).then(res => {
						if (res.data && res.data.code == 0) {
							this.$message.success('点赞成功')
							this.detail.thumbsupnum++
							this.$http.post('news/update',this.detail).then(obj=>{})
							this.getZan()
						}
					})
				}
			},
			collectClick(){
				if(this.collectType){
					this.$http.post('storeup/delete', [this.collectForm.id]).then(res => {
						if (res.data && res.data.code == 0) {
							this.$message.success('取消成功')
							this.detail.storeupnum--
							this.$http.post('news/update',this.detail).then(obj=>{})
							this.getCollect()
						}
					})
				}else{
					let data = {
						name: this.detail.title,
						picture: this.detail.picture,
						refid: this.detail.id,
						type: '1',
						tablename: 'news',
						userid: Number(localStorage.getItem('frontUserid')),
					}
					this.$http.post('storeup/add', data).then(res => {
						if (res.data && res.data.code == 0) {
							this.detail.storeupnum++
							this.$http.post('news/update',this.detail).then(obj=>{})
							this.$message.success('收藏成功')
							this.getCollect()
						}
					})
				}
			},
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	
	.news-detail-box {
				padding: 0 16%;
				margin: 10px auto;
				background: none;
				width: 100%;
				position: relative;
				.news-detail-view {
						width: 100%;
						.title-box {
								margin: 0;
								color: #fff;
								background: url(http://codegen.caihongy.cn/20250123/25ade25938c8405e935839fec0b21be5.png) center center/100% 100%;
								width: 400px;
								font-size: 30px;
								line-height: 80px;
								text-align: center;
								div {
										margin: 10px 0;
										color: #fff;
										background: none;
										font-size: 30px;
										line-height: 80px;
										text-align: center;
									}
			}
			.news-detail {
								padding: 20px;
								background: #fff;
								width: 100%;
								border-color: #C4C4C4;
								border-width: 1px 1px 0;
								position: relative;
								border-style: solid;
								.detail-title {
										color: #000;
										font-size: 18px;
										border-color: #C4C4C4;
										border-width: 0 0 1px ;
										line-height: 50px;
										border-style: solid;
										text-align: center;
									}
				.infoBox {
										display: flex;
										width: 100%;
										border-color: #C4C4C4;
										border-width: 0 0 1px ;
										justify-content: center;
										align-items: center;
										border-style: solid;
										.time_item {
												padding: 0 10px;
												.icon {
														margin: 0 4px 0 0;
														color: #23469A;
														font-size: 14px;
														line-height: 40px;
													}
						.label {
														color: #23469A;
														font-size: 14px;
														line-height: 40px;
													}
						.text {
														color: #23469A;
														font-size: 14px;
														line-height: 40px;
													}
					}
					.publisher_item {
												padding: 0 10px;
												.icon {
														margin: 0 4px 0 0;
														color: #23469A;
														font-size: 14px;
														line-height: 40px;
													}
						.label {
														color: #23469A;
														font-size: 14px;
														line-height: 40px;
													}
						.text {
														color: #23469A;
														font-size: 14px;
														line-height: 40px;
													}
					}
					.like_item {
												padding: 0 10px;
												.icon {
														margin: 0 4px 0 0;
														color: #23469A;
														font-size: 14px;
														line-height: 40px;
													}
						.label {
														color: #23469A;
														font-size: 14px;
														line-height: 40px;
													}
						.text {
														color: #23469A;
														font-size: 14px;
														line-height: 40px;
													}
					}
					.collect_item {
												padding: 0 10px;
												.icon {
														margin: 0 4px 0 0;
														color: #23469A;
														font-size: 14px;
														line-height: 40px;
													}
						.label {
														color: #23469A;
														font-size: 14px;
														line-height: 40px;
													}
						.text {
														color: #23469A;
														font-size: 14px;
														line-height: 40px;
													}
					}
					.view_item {
												padding: 0 10px;
												.icon {
														margin: 0 4px 0 0;
														color: #23469A;
														font-size: 14px;
														line-height: 40px;
													}
						.label {
														color: #23469A;
														font-size: 14px;
														line-height: 40px;
													}
						.text {
														color: #23469A;
														font-size: 14px;
														line-height: 40px;
													}
					}
				}
				.operate {
										padding: 10px 0;
										display: flex;
										width: 100%;
										justify-content: center;
										align-items: center;
										.zan-btn {
												cursor: pointer;
												padding: 0 15px;
												background: #23469A;
												display: flex;
												justify-content: center;
												align-items: center;
												.icon {
														margin: 0 5px 0 0;
														color: #fff;
														font-size: 14px;
														line-height: 30px;
													}
						.text {
														color: #fff;
														font-size: 14px;
														line-height: 30px;
													}
					}
					.zan-btn:hover {
												background: #23469A80;
												.icon {
														color: #fff;
													}
						.text {
														color: #fff;
													}
					}
					.collect-btn {
												cursor: pointer;
												padding: 0 15px;
												background: #E8D6B0;
												display: flex;
												justify-content: center;
												align-items: center;
												.icon {
														margin: 0 5px 0 0;
														color: #fff;
														font-size: 14px;
														line-height: 30px;
													}
						.text {
														color: #fff;
														font-size: 14px;
														line-height: 30px;
													}
					}
					.collect-btn:hover {
												background: #E8D6B080;
												.icon {
														color: #fff;
													}
						.text {
														color: #fff;
													}
					}
				}
				.content-detail {
										width: 100%;
										height: auto;
									}
			}
			.option-box {
								padding: 15px 0;
								margin: 0 0 20px;
								background: #fff;
								display: flex;
								width: 100%;
								border-color: #C4C4C4;
								border-width: 0 1px 1px;
								justify-content: center;
								border-style: solid;
								height: auto;
								.prev-btn {
										padding: 0 10px;
										background: #23469A;
										.text {
												color: #fff;
												font-size: 14px;
												line-height: 30px;
											}
					.icon {
												color: #fff;
												font-size: 14px;
												line-height: 30px;
											}
				}
				.prev-btn:hover {
										background: #23469A80;
										.text {
												color: #fff;
											}
					.icon {
												color: #fff;
											}
				}
				.next-btn {
										padding: 0 10px;
										background: #E8D6B0;
										.text {
												color: #fff;
												font-size: 14px;
												line-height: 30px;
											}
					.icon {
												color: #fff;
												font-size: 14px;
												line-height: 30px;
											}
				}
				.next-btn:hover {
										background: #E8D6B080;
										.text {
												color: #fff;
											}
					.icon {
												color: #fff;
											}
				}
			}
			.hot {
								box-shadow: 0 0px 0px rgba(0,0,0,.1);
								padding: 15px 0;
								background: none;
								width: 100%;
								height: auto;
								order: 8;
								.hot-title {
										margin: 0;
										color: #fff;
										background: url(http://codegen.caihongy.cn/20250123/25ade25938c8405e935839fec0b21be5.png) center center/100% 100%;
										width: 400px;
										font-size: 30px;
										line-height: 80px;
										text-align: center;
									}
				.hot-list {
										padding: 15px 0 0;
										background: none;
										display: flex;
										width: 100%;
										flex-wrap: wrap;
										height: auto;
										.hot-item {
												border: 1px solid rgb(232, 214, 176);
												border-radius: 10px;
												padding: 15px;
												margin: 0 1% 0 0;
												background: #fff;
												flex: 1;
												width: 18%;
												height: auto;
												img {
														border-radius: 10px;
														object-fit: cover;
														display: block;
														width: 100%;
														height: 100px;
													}
						.hot-name {
														padding: 0;
														color: #000;
														font-size: 15px;
														line-height: 30px;
													}
						.hot-time {
														padding: 0 5px;
														color: #999;
														font-size: 12px;
														line-height: 12px;
														text-align: right;
													}
					}
				}
			}
			.news {
								box-shadow: 0 0px 0px rgba(0,0,0,.1);
								padding: 15px 0;
								background: none;
								width: 100%;
								height: auto;
								order: 8;
								.news-title {
										margin: 0;
										color: #fff;
										background: url(http://codegen.caihongy.cn/20250123/25ade25938c8405e935839fec0b21be5.png) center center/100% 100%;
										width: 400px;
										font-size: 30px;
										line-height: 80px;
										text-align: center;
									}
				.news-list {
										padding: 15px 0 0;
										background: none;
										display: flex;
										width: 100%;
										flex-wrap: wrap;
										height: auto;
										.news-item {
												border: 1px solid rgb(232, 214, 176);
												border-radius: 10px;
												padding: 15px;
												margin: 0 1% 0 0;
												background: #fff;
												flex: 1;
												width: 18%;
												height: auto;
												img {
														border-radius: 10px;
														object-fit: cover;
														display: block;
														width: 100%;
														height: 100px;
													}
						.news-name {
														padding: 0;
														color: #000;
														font-size: 15px;
														line-height: 30px;
													}
						.news-time {
														padding: 0 5px;
														color: #999;
														font-size: 12px;
														line-height: 12px;
														text-align: right;
													}
					}
				}
			}
		}
	}
</style>