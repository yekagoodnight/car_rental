<template>
	<div class="home-preview">




		<!-- 新闻资讯 -->
		<div id="animate_newsnews" class="news animate__animated">
			<div class="news_title_box">
				<span class="news_title">新闻资讯</span>
				<span class="news_subhead">{{'news'.toUpperCase()}}</span>
			</div>
			<!-- 样式四 -->
			<div v-if="newsList.length" class="list list4 index-pv1">
				<div @click="toDetail('newsDetail', newsList[0])" v-if="newsList.length>0" class="list-item1 animation-box">
					<div class="name line1">{{newsList[0].title}}</div>
					<div class="desc line3">{{newsList[0].introduction}}</div>
					<div class="time_item">
						<span class="icon iconfont icon-shijian21"></span>
						<span class="label">发布时间：</span>
						<span class="text">{{newsList[0].addtime.split(' ')[0]}}</span>
					</div>
					<div class="publisher_item">
						<span class="icon iconfont icon-geren16"></span>
						<span class="label">发布人：</span>
						<span class="text">{{newsList[0].name}}</span>
					</div>
					<div class="like_item">
						<span class="icon iconfont icon-zan10"></span>
						<span class="label">点赞数：</span>
						<span class="text">{{newsList[0].thumbsupnum}}</span>
					</div>
					<div class="collect_item">
						<span class="icon iconfont icon-shoucang10"></span>
						<span class="label">收藏量：</span>
						<span class="text">{{newsList[0].storeupnum}}</span>
					</div>
					<div class="view_item">
						<span class="icon iconfont icon-chakan9"></span>
						<span class="label">点击量：</span>
						<span class="text">{{newsList[0].clicknum}}</span>
					</div>
				</div>
				<div @click="toDetail('newsDetail', newsList[0])" v-if="newsList.length>0" class="list-item2 animation-box">
					<img :src="baseUrl + newsList[0].picture" alt="">
				</div>
				<div @click="toDetail('newsDetail', newsList[1])" v-if="newsList.length>1" class="list-item3 animation-box">
					<div class="name line1">{{newsList[1].title}}</div>
					<div class="desc  line3">{{newsList[1].introduction}}</div>
					<div class="time_item">
						<span class="icon iconfont icon-shijian21"></span>
						<span class="label">发布时间：</span>
						<span class="text">{{newsList[1].addtime.split(' ')[0]}}</span>
					</div>
					<div class="publisher_item">
						<span class="icon iconfont icon-geren16"></span>
						<span class="label">发布人：</span>
						<span class="text">{{newsList[1].name}}</span>
					</div>
					<div class="like_item">
						<span class="icon iconfont icon-zan10"></span>
						<span class="label">点赞数：</span>
						<span class="text">{{newsList[1].thumbsupnum}}</span>
					</div>
					<div class="collect_item">
						<span class="icon iconfont icon-shoucang10"></span>
						<span class="label">收藏量：</span>
						<span class="text">{{newsList[1].storeupnum}}</span>
					</div>
					<div class="view_item">
						<span class="icon iconfont icon-chakan9"></span>
						<span class="label">点击量：</span>
						<span class="text">{{newsList[1].clicknum}}</span>
					</div>
				</div>
				<div @click="toDetail('newsDetail', newsList[1])" v-if="newsList.length>1" class="list-item4 animation-box">
					<img :src="baseUrl + newsList[1].picture" alt="">
				</div>
				<div @click="toDetail('newsDetail', newsList[2])" v-if="newsList.length>2" class="list-item5 animation-box">
					<div class="name  line1">{{newsList[2].title}}</div>
					<div class="desc  line3">{{newsList[2].introduction}}</div>
					<div class="time_item">
						<span class="icon iconfont icon-shijian21"></span>
						<span class="label">发布时间：</span>
						<span class="text">{{newsList[2].addtime.split(' ')[0]}}</span>
					</div>
					<div class="publisher_item">
						<span class="icon iconfont icon-geren16"></span>
						<span class="label">发布人：</span>
						<span class="text">{{newsList[2].name}}</span>
					</div>
					<div class="like_item">
						<span class="icon iconfont icon-zan10"></span>
						<span class="label">点赞数：</span>
						<span class="text">{{newsList[2].thumbsupnum}}</span>
					</div>
					<div class="collect_item">
						<span class="icon iconfont icon-shoucang10"></span>
						<span class="label">收藏量：</span>
						<span class="text">{{newsList[2].storeupnum}}</span>
					</div>
					<div class="view_item">
						<span class="icon iconfont icon-chakan9"></span>
						<span class="label">点击量：</span>
						<span class="text">{{newsList[2].clicknum}}</span>
					</div>
				</div>
				<div @click="toDetail('newsDetail', newsList[2])" v-if="newsList.length>2" class="list-item6 animation-box">
					<img :src="baseUrl + newsList[2].picture" alt="">
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('news')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 新闻资讯 -->
		<!-- 商品推荐 -->
		<div id="animate_recommendqichechuzu" class="recommend animate__animated">
			<div class="recommend_title_box">
				<span class="recommend_title">汽车租赁推荐</span>
				<span class="recommend_subhead">{{'qichechuzu'.toUpperCase()}} RECOMMEND</span>
			</div>
			<div class="list list18 index-pv1">
				<div class="list-body" v-if="qichechuzuRecommend.length">
					<div class="list-body-left">
						<div class="list-item1" @click="toDetail('qichechuzuDetail', qichechuzuRecommend[0])">
							<div class="infoBox">
								<div class="name">{{qichechuzuRecommend[0].zulinbiaoti}}</div>
								<div class="time_item">
									<span class="icon iconfont icon-shijian21"></span>
									<span class="label">发布时间：</span>
									<span class="text">{{qichechuzuRecommend[0].addtime.split(' ')[0]}}</span>
								</div>
								<div class="publisher_item">
									<span class="icon iconfont icon-geren16"></span>
									<span class="label">发布人：</span>
									<span class="text">{{qichechuzuRecommend[0].cheshangzhanghao}}</span>
								</div>
								<div class="collect_item">
									<span class="icon iconfont icon-shoucang10"></span>
									<span class="label">收藏量：</span>
									<span class="text">{{qichechuzuRecommend[0].storeupnum}}</span>
								</div>
								<div class="view_item">
									<span class="icon iconfont icon-chakan9"></span>
									<span class="label">点击量：</span>
									<span class="text">{{qichechuzuRecommend[0].clicknum}}</span>
								</div>
							</div>
							<div class="img">
								<img v-if="preHttp(qichechuzuRecommend[0].qichetupian)" :src="qichechuzuRecommend[0].qichetupian.split(',')[0]" alt="" />
								<img v-else :src="baseUrl + (qichechuzuRecommend[0].qichetupian?qichechuzuRecommend[0].qichetupian.split(',')[0]:'')" alt="" />
							</div>
						</div>
						<div class="list-item2" @click="toDetail('qichechuzuDetail', qichechuzuRecommend[1])">
							<div class="img">
								<img v-if="preHttp(qichechuzuRecommend[1].qichetupian)" :src="qichechuzuRecommend[1].qichetupian.split(',')[0]" alt="" />
								<img v-else :src="baseUrl + (qichechuzuRecommend[1].qichetupian?qichechuzuRecommend[1].qichetupian.split(',')[0]:'')" alt="" />
							</div>
							<div class="infoBox">
								<div class="name">{{qichechuzuRecommend[1].zulinbiaoti}}</div>
								<div class="time_item">
									<span class="icon iconfont icon-shijian21"></span>
									<span class="label">发布时间：</span>
									<span class="text">{{qichechuzuRecommend[1].addtime.split(' ')[0]}}</span>
								</div>
								<div class="publisher_item">
									<span class="icon iconfont icon-geren16"></span>
									<span class="label">发布人：</span>
									<span class="text">{{qichechuzuRecommend[1].cheshangzhanghao}}</span>
								</div>
								<div class="collect_item">
									<span class="icon iconfont icon-shoucang10"></span>
									<span class="label">收藏量：</span>
									<span class="text">{{qichechuzuRecommend[1].storeupnum}}</span>
								</div>
								<div class="view_item">
									<span class="icon iconfont icon-chakan9"></span>
									<span class="label">点击量：</span>
									<span class="text">{{qichechuzuRecommend[1].clicknum}}</span>
								</div>
							</div>
						</div>
					</div>
					<div class="list-body-right">
						<div class="swiper-container recommendSwiper18 recommend-list-18qichechuzu">
							<div class="swiper-wrapper">
								<div class="swiper-slide" v-for="(item,index) in qichechuzuRecommend" :key="index" v-if="index>2">
									<div class="list-item" @click="toDetail('qichechuzuDetail', item)">
										<div class="img">
											<img v-if="preHttp(item.qichetupian)" :src="item.qichetupian.split(',')[0]" alt="" />
											<img v-else :src="baseUrl + (item.qichetupian?item.qichetupian.split(',')[0]:'')" alt="" />
										</div>
										<div class="infoBox">
											<div class="name">{{item.zulinbiaoti}}</div>
											<div class="time_item">
												<span class="icon iconfont icon-shijian21"></span>
												<span class="label">发布时间：</span>
												<span class="text">{{item.addtime.split(' ')[0]}}</span>
											</div>
											<div class="publisher_item">
												<span class="icon iconfont icon-geren16"></span>
												<span class="label">发布人：</span>
												<span class="text">{{item.cheshangzhanghao}}</span>
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
								</div>
							</div>
							<div class="swiper-button-prev"></div>
							<div class="swiper-button-next"></div>
							<div class="swiper-pagination"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('qichechuzu')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 商品推荐 -->
	</div>
</template>

<script>
import 'animate.css'
import Swiper from "swiper";

	export default {
		//数据集合
		data() {
			return {
				baseUrl: '',
				newsList: [],
				qichechuzuRecommend: [],



				recommendListSwiper18qichechuzu: null,
				recommendIndex18qichechuzu: 0,
				recommendList18qichechuzu: [],
				recommendColumn18qichechuzu: '',


			}
		},
		created() {
			this.baseUrl = this.$config.baseUrl;
			this.getNewsList();
			this.getList();
			this.qichechuzuRecommendCate()
		},
		mounted() {
			window.addEventListener('scroll', this.handleScroll)
			setTimeout(()=>{
				this.handleScroll()
			},100)
			
			this.swiperChanges()
		},
		beforeDestroy() {
			window.removeEventListener('scroll', this.handleScroll)
		},
		//方法集合
		methods: {
			swiperChanges() {
				if (this['recommendListSwiper18qichechuzu']) this['recommendListSwiper18qichechuzu'].destroy()
				setTimeout(()=>{
					this['recommendListSwiper18qichechuzu'] = new Swiper(".recommend-list-18qichechuzu", {"navigation":{"nextEl":".swiper-button-next","prevEl":".swiper-button-prev"},"pagination":{"el":".swiper-pagination","clickable":true},"loop":false,"coverflowEffect":{"rotate":10,"stretch":10,"depth":60,"modifier":3,"slideShadows":false},"centeredSlides":true,"slidesPerView":1,"spaceBetween":0,"autoplay":{"delay":3000,"disableOnInteraction":false}})
				},750)
			},
			qichechuzuRecommendCate(){
				this.$http.get('qicheleixing/list',{}).then(res => {
					if (res.data.code == 0) {
						this.recommendList18qichechuzu = res.data.data.list
					}
				});
				this.recommendColumn18qichechuzu = 'qicheleixing'
			},
			async recommendIndexClick18(index, name) {
				this['recommendIndex18' + name] = index
				await this.getList()
			},


			handleScroll() {
				let arr = [
					{id:'about',css:'animate__'},
					{id:'system',css:'animate__'},
					{id:'animate_recommendqichechuzu',css:'animate__'},
					{id:'animate_newsnews',css:'animate__'},
				]
			
				for (let i in arr) {
					let doc = document.getElementById(arr[i].id)
					if (doc) {
						let top = doc.offsetTop
						let win_top = window.innerHeight + window.pageYOffset
						// console.log(top,win_top)
						if (win_top > top && doc.classList.value.indexOf(arr[i].css) < 0) {
							// console.log(doc)
							doc.classList.add(arr[i].css)
						}
					}
				}
			},
			preHttp(str) {
				return str && str.substr(0,4)=='http';
			},
			getNewsList() {
				let data = {
					page: 1,
					limit: 6,
					sort: 'addtime',
					order: 'desc'
				}
				this.$http.get('news/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.newsList = res.data.data.list;
					
					}
				});
			},
			getList() {
				let autoSortUrl = "";
				let data = {}
				autoSortUrl = "qichechuzu/autoSort";
				if(localStorage.getItem('frontToken')) {
					autoSortUrl = "qichechuzu/autoSort2";
				}
				data = {
					page: 1,
					limit: 6,
				}
				if(this.recommendIndex18qichechuzu != 0){
					data.qicheleixing = this.recommendList18qichechuzu[this.recommendIndex18qichechuzu - 1].qicheleixing
				}
				this.$http.get(autoSortUrl, {params: data}).then(res => {
					if (res.data.code == 0) {
						this.qichechuzuRecommend = res.data.data.list;
					}
				});
			
			},
			toDetail(path, item) {
				this.$router.push({path: '/index/' + path, query: {id: item.id}});
			},
			moreBtn(path) {
				this.$router.push({path: '/index/' + path});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.home-preview {
		margin: 0px auto;
		flex-direction: column;
		display: flex;
		width: 100%;
		.news {
			padding: 30px 16%;
			margin: 0;
			background: #fff;
			width: 100%;
			position: relative;
			order: 5;
			.news_title_box {
				margin: 0;
				background: url(http://codegen.caihongy.cn/20250123/25ade25938c8405e935839fec0b21be5.png) center center/100% 100%;
				width: 400px;
				line-height: 80px;
				text-align: center;
				.news_title {
					color: #fff;
					width: 100%;
					font-size: 30px;
					text-align: center;
				}
				.news_subhead {
					margin: 0 0 10px;
					color: #999;
					display: none;
					width: 100%;
					font-size: 20px;
					line-height: 1.5;
					text-align: center;
				}
			}
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 2px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(1deg, 1deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list4 {
				padding: 10px 0;
				background: #fff;
				display: flex;
				width: 100%;
				justify-content: center;
				flex-wrap: wrap;
				height: auto;
				.list-item1 {
					border-radius: 10px;
					padding: 30px;
					clip-path: polygon(0% 0,35% 0,35% 5%,65% 5%,65% 0%, 100% 0, 100% 100%, 0% 100%);
					background: #ECE1C3;
					display: flex;
					width: calc(100% / 3);
					justify-content: flex-start;
					align-items: center;
					flex-wrap: wrap;
					height: 260px;
					.name {
						margin: 0 0 10px;
						overflow: hidden;
						color: #000;
						white-space: nowrap;
						width: 100%;
						font-size: 18px;
						text-overflow: ellipsis;
						text-align: left;
					}
					.desc {
						padding: 0;
						margin: 0 0 10px;
						color: #717171;
						width: 100%;
						font-size: 15px;
					}
					.time_item {
						padding: 0 20px 0 0;
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
						padding: 0 20px 0 0;
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
						padding: 0 20px 0 0;
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
						padding: 0 20px 0 0;
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
						padding: 0 20px 0 0;
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
				.list-item2 {
					background: #f5f5f5;
					width: calc(100% / 3);
					height: 260px;
					img {
						border-radius: 10px;
						object-fit: cover;
						width: 100%;
						height: 100%;
					}
				}
				
				.list-item3 {
					border-radius: 10px;
					padding: 30px;
					clip-path: polygon(0% 0,35% 0,35% 5%,65% 5%,65% 0%, 100% 0, 100% 100%, 0% 100%);
					background: #ECE1C3;
					display: flex;
					width: calc(100% / 3);
					justify-content: flex-start;
					align-items: center;
					flex-wrap: wrap;
					height: 260px;
					.name {
						margin: 0 0 10px;
						overflow: hidden;
						color: #000;
						white-space: nowrap;
						width: 100%;
						font-size: 18px;
						text-overflow: ellipsis;
						text-align: left;
					}
					.desc {
						padding: 0;
						margin: 0 0 10px;
						color: #717171;
						width: 100%;
						font-size: 15px;
					}
					.time_item {
						padding: 0 20px 0 0;
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
						padding: 0 20px 0 0;
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
						padding: 0 20px 0 0;
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
						padding: 0 20px 0 0;
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
						padding: 0 20px 0 0;
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
				.list-item4 {
					background: #f5f5f5;
					width: calc(100% / 3);
					height: 260px;
					img {
						border-radius: 10px;
						object-fit: cover;
						width: 100%;
						height: 100%;
					}
				}
				.list-item5 {
					border-radius: 10px;
					padding: 30px;
					clip-path: polygon(0% 0, 100% 0, 100% 100%,65% 100%,65% 95%,35% 95%,35% 100%, 0% 100%);
					background: #ECE1C3;
					display: flex;
					width: calc(100% / 3);
					justify-content: flex-start;
					align-items: center;
					flex-wrap: wrap;
					height: 260px;
					.name {
						margin: 0 0 10px;
						overflow: hidden;
						color: #000;
						white-space: nowrap;
						width: 100%;
						font-size: 18px;
						text-overflow: ellipsis;
						text-align: left;
					}
					.desc {
						padding: 0;
						margin: 0 0 10px;
						color: #717171;
						width: 100%;
						font-size: 15px;
					}
					.time_item {
						padding: 0 20px 0 0;
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
						padding: 0 20px 0 0;
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
						padding: 0 20px 0 0;
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
						padding: 0 20px 0 0;
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
						padding: 0 20px 0 0;
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
				.list-item6 {
					background: #f5f5f5;
					width: calc(100% / 3);
					height: 260px;
					img {
						border-radius: 10px;
						object-fit: cover;
						width: 100%;
						height: 100%;
					}
				}
			}
			.moreBtn {
				border: 0;
				border-radius: 5px;
				padding: 0 20px;
				margin: 10px auto;
				top: 45px;
				background: #23469A;
				display: block;
				width: auto;
				line-height: 32px;
				position: absolute;
				right: 16%;
				text-align: center;
				.text {
					color: #f5f5f5;
					font-size: 12px;
				}
				.icon {
					color: #f5f5f5;
					display: none;
					font-size: 12px;
				}
			}
		}
		.recommend {
			padding: 30px 16%;
			margin: 0;
			background: #fff;
			width: 100%;
			position: relative;
			order: 3;
			.recommend_title_box {
				margin: 0;
				background: url(http://codegen.caihongy.cn/20250123/25ade25938c8405e935839fec0b21be5.png) center center/100% 100%;
				width: 400px;
				line-height: 80px;
				text-align: center;
				.recommend_title {
					color: #fff;
					width: 100%;
					font-size: 30px;
					text-align: center;
				}
				.recommend_subhead {
					margin: 0 0 10px;
					color: #999;
					display: none;
					width: 100%;
					font-size: 20px;
					line-height: 1.5;
					text-align: center;
				}
			}
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 10px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1.1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list18 {
				background: #fff;
				width: 100%;
				.tab_view {
					margin: 10px 0;
					display: flex;
					width: 100%;
					justify-content: flex-start;
					.tab {
						cursor: pointer;
						border: 3px solid rgb(35, 70, 154);
						border-radius: 0 0 30px 0;
						margin: 0 5px 10px;
						color: #23469A;
						background: linear-gradient(180.00deg, rgb(145, 177, 255),rgb(222, 229, 253) 98.473%);
						display: block;
						width: 140px;
						font-size: 15px;
						line-height: 50px;
						text-align: center;
						height: auto;
					}
					.tab:hover {
						border: 3px solid rgb(232, 214, 176);
						color: #AD8B44;
						background: linear-gradient(180.00deg, rgb(242, 229, 195),rgb(253, 245, 222) 98.473%);
					}
					.tab.tabActive {
						border: 3px solid rgb(232, 214, 176);
						color: #AD8B44;
						background: linear-gradient(180.00deg, rgb(242, 229, 195),rgb(253, 245, 222) 98.473%);
					}
				}
				.list-body {
					padding: 0 10px;
					display: flex;
					width: 100%;
					.list-body-left {
						margin: 0 auto 0 0;
						width: 49%;
						.list-item1 {
							display: flex;
							width: 100%;
							align-items: center;
							transition: all .3s;
							.infoBox {
								border: 1px solid rgb(232, 214, 176);
								padding: 20px;
								display: flex;
								width: 52%;
								justify-content: flex-start;
								flex-wrap: wrap;
								height: 300px;
								.name {
									color: #000;
									font-weight: bold;
									width: 100%;
									font-size: 15px;
									line-height: 1.5;
								}
								.price {
									padding: 10px 0;
									color: #FF0000;
									width: 100%;
									font-size: 13px;
									line-height: 1.5;
								}
								.time_item {
									padding: 0 20px 0 0;
									order: 5;
									.icon {
										margin: 0 5px 0 0;
										color: #E0C936;
										font-size: 14px;
										line-height: 25px;
									}
									.label {
										color: #000;
										display: none;
										font-size: 12px;
										line-height: 1.5;
									}
									.text {
										color: #E0C936;
										font-size: 14px;
										line-height: 25px;
									}
								}
								.publisher_item {
									padding: 0 20px 0 0;
									width: auto;
									order: 4;
									.icon {
										margin: 0 5px 0 0;
										color: #43BAB1;
										font-size: 14px;
										line-height: 25px;
									}
									.label {
										color: #000;
										display: none;
										font-size: 12px;
										line-height: 1.5;
									}
									.text {
										color: #43BAB1;
										font-size: 14px;
										line-height: 25px;
									}
								}
								.like_item {
									padding: 0 20px 0 0;
									order: 1;
									.icon {
										margin: 0 5px 0 0;
										color: #23469A;
										font-size: 14px;
										line-height: 25px;
									}
									.label {
										color: #000;
										display: none;
										font-size: 12px;
										line-height: 1.5;
									}
									.text {
										color: #23469A;
										font-size: 14px;
										line-height: 25px;
									}
								}
								.collect_item {
									padding: 0 20px 0 0;
									order: 2;
									.icon {
										margin: 0 5px 0 0;
										color: #AD8B44;
										font-size: 14px;
										line-height: 25px;
									}
									.label {
										color: #000;
										display: none;
										font-size: 12px;
										line-height: 1.5;
									}
									.text {
										color: #AD8B44;
										font-size: 14px;
										line-height: 25px;
									}
								}
								.view_item {
									padding: 0 20px 0 0;
									order: 3;
									.icon {
										margin: 0 5px 0 0;
										color: #A76850;
										font-size: 14px;
										line-height: 25px;
									}
									.label {
										color: #000;
										display: none;
										font-size: 12px;
										line-height: 1.5;
									}
									.text {
										color: #A76850;
										font-size: 14px;
										line-height: 25px;
									}
								}
							}
							.img {
								width: 48%;
								transition: all .3s;
								height: 300px;
								img {
									transform: scale(1);
									object-fit: cover;
									display: block;
									width: 100%;
									transition: all .3s;
									height: 100%;
								}
							}
						}
						.list-item1:hover {
							transform: translate3d(0px, 0px, 0px);
							.infoBox {
								background: #23469A;
								.name {
									color: #fff;
								}
								.price {
									color: #fff;
								}
								.time_item {
									.icon {
										color: #fff;
									}
									.label {
										color: #fff;
									}
									.text {
										color: #fff;
									}
								}
								.publisher_item {
									.icon {
										color: #fff;
									}
									.label {
										color: #fff;
									}
									.text {
										color: #fff;
									}
								}
								.like_item {
									.icon {
										color: #fff;
									}
									.label {
										color: #fff;
									}
									.text {
										color: #fff;
									}
								}
								.collect_item {
									.icon {
										color: #fff;
									}
									.label {
										color: #fff;
									}
									.text {
										color: #fff;
									}
								}
								.view_item {
									.icon {
										color: #fff;
									}
									.label {
										color: #fff;
									}
									.text {
										color: #fff;
									}
								}
							}
							.img {
								overflow: hidden;
								img {
									transform: scale(1.2);
								}
							}
						}
						.list-item2 {
							display: flex;
							width: 100%;
							align-items: center;
							transition: all .3s;
							.img {
								width: 48%;
								transition: all .3s;
								height: 300px;
								img {
									transform: scale(1);
									object-fit: cover;
									display: block;
									width: 100%;
									transition: all .3s;
									height: 100%;
								}
							}
							.infoBox {
								border: 1px solid rgb(232, 214, 176);
								padding: 20px;
								display: flex;
								width: 52%;
								justify-content: flex-start;
								flex-wrap: wrap;
								height: 300px;
								.name {
									color: #000;
									font-weight: bold;
									width: 100%;
									font-size: 15px;
									line-height: 1.5;
								}
								.price {
									padding: 10px 0;
									color: #FF0000;
									width: 100%;
									font-size: 13px;
									line-height: 1.5;
								}
								.time_item {
									padding: 0 20px 0 0;
									order: 5;
									.icon {
										margin: 0 5px 0 0;
										color: #E0C936;
										font-size: 14px;
										line-height: 25px;
									}
									.label {
										color: #000;
										display: none;
										font-size: 12px;
										line-height: 1.5;
									}
									.text {
										color: #E0C936;
										font-size: 14px;
										line-height: 25px;
									}
								}
								.publisher_item {
									padding: 0 20px 0 0;
									width: auto;
									order: 4;
									.icon {
										margin: 0 5px 0 0;
										color: #43BAB1;
										font-size: 14px;
										line-height: 25px;
									}
									.label {
										color: #000;
										display: none;
										font-size: 12px;
										line-height: 1.5;
									}
									.text {
										color: #43BAB1;
										font-size: 14px;
										line-height: 25px;
									}
								}
								.like_item {
									padding: 0 20px 0 0;
									order: 1;
									.icon {
										margin: 0 5px 0 0;
										color: #23469A;
										font-size: 14px;
										line-height: 25px;
									}
									.label {
										color: #000;
										display: none;
										font-size: 12px;
										line-height: 1.5;
									}
									.text {
										color: #23469A;
										font-size: 14px;
										line-height: 25px;
									}
								}
								.collect_item {
									padding: 0 20px 0 0;
									order: 2;
									.icon {
										margin: 0 5px 0 0;
										color: #AD8B44;
										font-size: 14px;
										line-height: 25px;
									}
									.label {
										color: #000;
										display: none;
										font-size: 12px;
										line-height: 1.5;
									}
									.text {
										color: #AD8B44;
										font-size: 14px;
										line-height: 25px;
									}
								}
								.view_item {
									padding: 0 20px 0 0;
									order: 3;
									.icon {
										margin: 0 5px 0 0;
										color: #A76850;
										font-size: 14px;
										line-height: 25px;
									}
									.label {
										color: #000;
										display: none;
										font-size: 12px;
										line-height: 1.5;
									}
									.text {
										color: #A76850;
										font-size: 14px;
										line-height: 25px;
									}
								}
							}
						}
						.list-item2:hover {
							transform: translate3d(0px, 0px, 0px);
							.img {
								overflow: hidden;
								img {
									transform: scale(1.2);
								}
							}
							.infoBox {
								background: #23469A;
								.name {
									color: #fff;
								}
								.price {
									color: #fff;
								}
								.time_item {
									.icon {
										color: #fff;
									}
									.label {
										color: #fff;
									}
									.text {
										color: #fff;
									}
								}
								.publisher_item {
									.icon {
										color: #fff;
									}
									.label {
										color: #fff;
									}
									.text {
										color: #fff;
									}
								}
								.like_item {
									.icon {
										color: #fff;
									}
									.label {
										color: #fff;
									}
									.text {
										color: #fff;
									}
								}
								.collect_item {
									.icon {
										color: #fff;
									}
									.label {
										color: #fff;
									}
									.text {
										color: #fff;
									}
								}
								.view_item {
									.icon {
										color: #fff;
									}
									.label {
										color: #fff;
									}
									.text {
										color: #fff;
									}
								}
							}
						}
					}
					.list-body-right {
						width: 49%;
						.list-item {
							border-radius: 10px;
							overflow: hidden;
							width: 100%;
							position: relative;
							.img {
								width: 100%;
								height: 600px;
								img {
									transform: scale(1);
									object-fit: cover;
									display: block;
									width: 100%;
									transition: all .3s;
									height: 100%;
								}
							}
							.infoBox {
								padding: 20px;
								left: 0;
								bottom: 0;
								background: rgba(35, 70, 154, 0.5);
								display: flex;
								width: 100%;
								justify-content: flex-start;
								position: absolute;
								flex-wrap: wrap;
								.name {
									color: #fff;
									width: 100%;
									font-size: 14px;
									line-height: 1.5;
								}
								.price {
									padding: 10px 0;
									color: #FF0000;
									width: 100%;
									font-size: 13px;
									line-height: 1.5;
								}
								.time_item {
									padding: 0 20px 0 0;
									order: 5;
									.icon {
										margin: 0 5px 0 0;
										color: #fff;
										font-size: 14px;
										line-height: 25px;
									}
									.label {
										display: none;
									}
									.text {
										color: #fff;
										font-size: 14px;
										line-height: 25px;
									}
								}
								.publisher_item {
									padding: 0 20px 0 0;
									order: 4;
									.icon {
										margin: 0 5px 0 0;
										color: #fff;
										font-size: 14px;
										line-height: 25px;
									}
									.label {
										display: none;
									}
									.text {
										color: #fff;
										font-size: 14px;
										line-height: 25px;
									}
								}
								.like_item {
									padding: 0 20px 0 0;
									order: 1;
									.icon {
										margin: 0 5px 0 0;
										color: #fff;
										font-size: 14px;
										line-height: 25px;
									}
									.label {
										display: none;
									}
									.text {
										color: #fff;
										font-size: 14px;
										line-height: 25px;
									}
								}
								.collect_item {
									padding: 0 20px 0 0;
									order: 2;
									.icon {
										margin: 0 5px 0 0;
										color: #fff;
										font-size: 14px;
										line-height: 25px;
									}
									.label {
										display: none;
									}
									.text {
										color: #fff;
										font-size: 14px;
										line-height: 25px;
									}
								}
								.view_item {
									padding: 0 20px 0 0;
									order: 3;
									.icon {
										margin: 0 5px 0 0;
										color: #fff;
										font-size: 14px;
										line-height: 25px;
									}
									.label {
										display: none;
									}
									.text {
										color: #fff;
										font-size: 14px;
										line-height: 25px;
									}
								}
							}
						}
						.swiper-button-prev {
							left: 10px;
							right: auto;
						}
						.swiper-button-prev::after {
							color: #23469A;
							font-size: 14px;
						}
						.swiper-button-next {
							left: auto;
							right: 10px;
						}
						.swiper-button-next::after {
							color: #23469A;
						}
						/deep/.swiper-pagination {
							transform: translate3d(0,0,0);
							z-index: 10;
							left: 0;
							bottom: 10px;
							width: 100%;
							position: absolute;
							transition: .3s opacity;
							text-align: center;
							.swiper-pagination-bullet {
								cursor: pointer;
								border-radius: 100%;
								margin: 0 4px;
								background: #AD8B44;
								display: inline-block;
								width: 8px;
								opacity: .2;
								height: 8px;
							}
							.swiper-pagination-bullet:hover {
								background: #AD8B44;
							}
							.swiper-pagination-bullet.swiper-pagination-bullet-active {
								background: #AD8B44;
							}
						}
					}
				}
			}
			.moreBtn {
				border: 0;
				border-radius: 5px;
				padding: 0 20px;
				margin: 10px auto;
				top: 45px;
				background: #23469A;
				display: block;
				width: auto;
				line-height: 32px;
				position: absolute;
				right: 16%;
				text-align: center;
				.text {
					color: #f5f5f5;
					font-size: 12px;
				}
				.icon {
					color: #f5f5f5;
					display: none;
					font-size: 12px;
				}
			}
		}
	}
</style>
