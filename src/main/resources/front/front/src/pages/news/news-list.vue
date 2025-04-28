<template>
	<div>
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="''">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index"><a>{{item.name}}</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
	
		<div class="news-preview-pv">
			<el-form :inline="true" :model="formSearch" class="list-form-pv">
				<el-form-item class="search-item">
					<el-input v-model="title" placeholder="标题"></el-input>
				</el-form-item>
				<el-button class="search-btn" type="primary" @click="getNewsList(1)">
					<span class="icon iconfont icon-chakan14"></span>
					搜索
				</el-button>
			</el-form>
			
			<!-- category -->
			<div class="category-list">
				<div class="item" @click="categoryClick(0)" :class="categoryIndex == 0 ? 'active' : ''">全部</div>
				<div v-for="(item,index) in categoryList" @click="categoryClick(index+1)" :key="index" class="item" :class="categoryIndex == index+1 ? 'active' : ''">{{item.typename}}</div>
			</div>
			<!-- 样式一 -->
			<div class="list1 index-pv1">
				<div v-for="item in newsList" :key="item.id" @click="toNewsDetail(item)" class="list-item animation-box">
					<img :src="baseUrl + item.picture" >
					<div class="name">{{item.title}}</div>
					<div class="desc">{{item.introduction}}</div>
					<div class="time_item">
						<span class="icon iconfont icon-shijian21"></span>
						<span class="label">发布时间：</span>
						<span class="text">{{item.addtime.split(' ')[0]}}</span>
					</div>
					<div class="publisher_item">
						<span class="icon iconfont icon-geren16"></span>
						<span class="label">发布人：</span>
						<span class="text">{{item.name}}</span>
					</div>
					<div class="like_item">
						<span class="icon iconfont icon-zan10"></span>
						<span class="label">点赞数：</span>
						<span class="text">{{item.thumbsupnum}}</span>
					</div>
					<div class="collect_item">
						<span class="icon iconfont icon-shoucang10"></span>
						<span class="label">收藏量：</span>
						<span class="text">{{item.storeupnum}}</span>
					</div>
					<div class="view_item">
						<span class="icon iconfont icon-chakan9"></span>
						<span class="label">点击量：</span>
						<span class="text">{{item.clicknum}}</span>
					</div>
				</div>
			</div>
		
			<el-pagination
				background
				id="pagination" class="pagination"
				:pager-count="7"
				:page-size="pageSize"
				:page-sizes="pageSizes"
				prev-text="<"
				next-text=">"
				:hide-on-single-page="true"
				:layout='["prev","pager","next","jumper"].join()'
				:total="total"
				@current-change="curChange"
				@prev-click="prevClick"
				@next-click="nextClick"
				></el-pagination>

			<!-- 热门信息 -->
			<div class="hot">
				<div class="hot-title">热门信息</div>
				<div class="hot-list">
					<div class="hot-item" v-for="item in hotList" :key="item.id" @click="toNewsDetail(item)">
						<img :src="baseUrl + item.picture" alt="">
						<div class="hot-name">{{ item.title }}</div>
						<div class="hot-time">{{item.addtime}}</div>
					</div>
				</div>
			</div>
			<!-- 最新动态 -->
			<div class="news">
				<div class="news-title">最新动态</div>
				<div class="news-list">
					<div class="news-item" v-for="item in recommendList" :key="item.id" @click="toNewsDetail(item)">
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
				baseUrl: this.$config.baseUrl,
				breadcrumbItem: [
				  {
					name: '新闻资讯'
				  }
				],
				newsList: [],
				total: 1,
				pageSize: 10,
				pageSizes: [],
				totalPage: 1,
				layouts: '',
				title: '',
				categoryIndex: 0,
				categoryList: [],
				hotList: [],
				recommendList: [],
			}
		},
		created() {
			this.getCategoryList()
			
			this.getHotList()
			this.getRecommendList()
		},
		//方法集合
		methods: {
			getCategoryList(){
				this.$http.get('newstype/list', {}).then(res => {
					if (res.data.code == 0) {
						this.categoryList = res.data.data.list;
						this.getNewsList(1);
					}
				});
			},
			categoryClick(index) {
				this.categoryIndex = index
				this.getNewsList()
			},
			getNewsList(page) {
				let params = {page, limit: this.pageSize,sort:'addtime',order:'desc'};
				let searchWhere = {};
				if(this.title != '') searchWhere.title = '%' + this.title + '%';
				if(this.categoryIndex!=0){
					searchWhere.typename = this.categoryList[this.categoryIndex - 1].typename
				}
				this.$http.get('news/list', {params: Object.assign(params, searchWhere)}).then(res => {
					if (res.data.code == 0) {
						this.newsList = res.data.data.list;
						this.total = res.data.data.total;
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
			getHotList(){
				let params = {page:1, limit: 4,sort:'addtime',order:'desc'};
				this.$http.get('news/autoSort', {params: params}).then(res => {
					if (res.data.code == 0) {
						this.hotList = res.data.data.list;
					}
				});
			},
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
			curChange(page) {
				this.getNewsList(page);
			},
			prevClick(page) {
				this.getNewsList(page);
			},
			nextClick(page) {
				this.getNewsList(page);
			},
			toNewsDetail(item) {
				this.$router.push({path: '/index/newsDetail', query: {id: item.id}});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.news-preview-pv {
				padding: 0 16%;
				margin: 0px auto;
				background: none;
				width: 100%;
				position: relative;
				.list-form-pv {
						padding: 10px;
						background: none;
						display: flex;
						width: 100%;
						justify-content: center;
						align-items: center;
						flex-wrap: wrap;
						height: auto;
						.search-item {
								margin: 0 10px;
								.el-input {
										width: 100%;
									}
				.el-input /deep/ .el-input__inner {
										border: 1px solid rgb(37, 72, 156);
										border-radius: 0;
										padding: 0 10px;
										margin: 0;
										outline: none;
										color: #000;
										background: #fff;
										width: 300px;
										font-size: 14px;
										line-height: 42px;
										height: 42px;
									}
			}
			.search-btn {
								cursor: pointer;
								border: 0;
								border-radius: 0;
								padding: 0px 15px;
								margin: 0;
								outline: none;
								color: #fff;
								background: #5D62DE;
								width: auto;
								font-size: 14px;
								line-height: 42px;
								height: 42px;
								.icon {
										margin: 0 10px 0 0;
										color: #fff;
										font-size: 14px;
									}
			}
		}
		.category-list {
						padding: 10px ;
						background: none;
						display: flex;
						width: 100%;
						height: auto;
						.item {
								cursor: pointer;
								border: 3px solid rgb(35, 70, 154);
								border-radius: 0 0 30px 0;
								padding: 5px 20px;
								margin: 0 10px 0 0;
								color: #23469A;
								background: linear-gradient(180.00deg, rgb(145, 177, 255),rgb(222, 229, 253) 98.473%);
								display: flex;
								align-items: center;
							}
			
			.item:hover {
								border: 3px solid rgb(232, 214, 176);
								color: #AD8B44;
								background: linear-gradient(180.00deg, rgb(242, 229, 195),rgb(253, 245, 222) 98.473%);
							}
			
			.item.active {
								border: 3px solid rgb(232, 214, 176);
								color: #AD8B44;
								background: linear-gradient(180.00deg, rgb(242, 229, 195),rgb(253, 245, 222) 98.473%);
							}
		}
		.list1 {
						padding: 10px 0;
						background: none;
						display: flex;
						width: 100%;
						justify-content: space-between;
						flex-wrap: wrap;
						height: auto;
						.list-item {
								border: 1px solid rgb(232, 214, 176);
								border-radius: 10px;
								padding: 15px;
								margin: 0 0 10px;
								background: #fff;
								display: flex;
								width: 48%;
								justify-content: space-between;
								position: relative;
								flex-wrap: wrap;
								height: auto;
								img {
										border-radius: 10px;
										object-fit: cover;
										display: block;
										width: 95%;
										height: 250px;
									}
				.name {
										padding: 5px 0;
										overflow: hidden;
										color: #000;
										white-space: nowrap;
										width: 100%;
										font-size: 15px;
										line-height: 32px;
										text-overflow: ellipsis;
									}
				.desc {
										padding: 5px 0;
										overflow: hidden;
										color: #717171;
										white-space: nowrap;
										width: 100%;
										font-size: 14px;
										line-height: 21px;
										text-overflow: ellipsis;
									}
				.time_item {
										padding: 0;
										width: 50%;
										text-align: right;
										order: 5;
										.icon {
												margin: 0 5px 0 0;
												color: #E0C936;
												font-size: 14px;
												line-height: 25px;
											}
					.label {
												color: #E0C936;
												font-size: 14px;
												line-height: 25px;
											}
					.text {
												color: #E0C936;
												font-size: 14px;
												line-height: 25px;
											}
				}
				.publisher_item {
										padding: 0;
										width: 50%;
										order: 4;
										.icon {
												margin: 0 5px 0 0;
												color: #43BAB1;
												font-size: 14px;
												line-height: 25px;
											}
					.label {
												color: #43BAB1;
												font-size: 14px;
												line-height: 25px;
											}
					.text {
												color: #43BAB1;
												font-size: 14px;
												line-height: 25px;
											}
				}
				.like_item {
										padding: 0;
										order: 1;
										.icon {
												margin: 0 5px 0 0;
												color: #23469A;
												font-size: 14px;
												line-height: 25px;
											}
					.label {
												color: #23469A;
												font-size: 14px;
												line-height: 25px;
											}
					.text {
												color: #23469A;
												font-size: 14px;
												line-height: 25px;
											}
				}
				.collect_item {
										padding: 0;
										order: 2;
										.icon {
												margin: 0 5px 0 0;
												color: #AD8B44;
												font-size: 14px;
												line-height: 25px;
											}
					.label {
												color: #AD8B44;
												font-size: 14px;
												line-height: 25px;
											}
					.text {
												color: #AD8B44;
												font-size: 14px;
												line-height: 25px;
											}
				}
				.view_item {
										padding: 0;
										order: 3;
										.icon {
												margin: 0 5px 0 0;
												color: #A76850;
												font-size: 14px;
												line-height: 25px;
											}
					.label {
												color: #A76850;
												font-size: 14px;
												line-height: 25px;
											}
					.text {
												color: #A76850;
												font-size: 14px;
												line-height: 25px;
											}
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
	
	.index-pv1 .animation-box {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
		z-index: initial;
	}
	
	.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1.1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				background: #ECE1C3;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
	}
	
	.index-pv1 .animation-box img {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
	}
	
	.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(1deg, -1deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
</style>
