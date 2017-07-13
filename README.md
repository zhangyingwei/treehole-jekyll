# treehole-jekyll [树洞]

我觉得数据库也需要重新设计

to-do-list

重新设计数据结构
重新设计数据库
紧紧是article部分
还有tag部分

重新规划设计一下,已不能完全按照 jekyll 的标准走 毕竟 jekyll 的文档有点烂

首页
  page 描述页面信息
    title
    url
    description
  posts 文章信息
    post 这个内容按照目前的情况走就行了
  paginator 分页信息
    去掉分页信息中保存的 post 毕竟多写一份会浪费一定的带宽 拖慢加载时间
    
  site 站点信息
    主要包括站点的配置信息与 yml 配置文件中的配置信息
    theme 把主题配置信息加到这里边
    
详情页
  site 站点信息
  page 页面信息
    title
    url
    path
    description
    等等
  post 文章信息
  
  
以上都做的差不多了

but
后台管理需要添加友链管理
每一篇文章都需要6篇相关文章 就是分类一样的文章 或者 标签 一样的文章
按照分类来提取文章好做
但是我觉得按照tag来提取文章更好一点

