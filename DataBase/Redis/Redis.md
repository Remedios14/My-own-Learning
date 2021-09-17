# Redis

REmote DIctionary Server 是是一个由 Salvatore Sanfilippo 写的 key-value 存储系统，是跨平台的**非关系型**数据库。是一个开源的使用 ANSI C 语言编写、遵守 BSD 协议、支持网络、可基于内存、分布式、可选持久性的键值对(Key-Value)存储数据库，并提供多种语言的 API。

Redis 通常被称为数据结构服务器，因为值（value）可以是字符串(String)、哈希(Hash)、列表(list)、集合(sets)和有序集合(sorted sets)等类型。

## 安装

1. 获取 tar.gz 文件后 `tar zxvf [文件名]`来解压，然后进入解压后的目录

2. 执行`make`后在 src 目录下得到服务程序 redis-server 和测试的客户端程序 redis-cli

3. ```shell
   ./redis-server [../redis.conf] # 启动，后面可选用指定配置文件启动
   ```

4. 启动服务进程后可以使用测试客户端程序 redis-cli 来和服务交互

   ```shell
   ./redis-cli
   redis> set foo bar
   redis>get foo
   ```

或者直接 apt 安装

1. ```shell
   sudo apt update
   sudo apt install redis-server
   ```

2. `redis-server`启动服务

3. `redis-cli`启动客户端

## Redis 配置

默认配置文件即为 `redis.conf` ，在客户端进程内用 CONFIG 命令查看或设置[配置项](https://www.runoob.com/redis/redis-conf.html)

### 获取

```shell
CONFIG GET *  # 获取全部配置
CONFIG GET <config_setting_name> # 获取特定配置
```

### 编辑

```shell
CONFIG SET <config_setting_name> <new_config_value> # 值一般以字符串形式传入
```

## Redis 数据类型

string、hash、list、set、zset (sorted set)

- String (字符串)

  - 一个 key 对应一个 value

  - 二进制安全的，可以包含任何数据

  - 最大能存储 512MB

  - ```shell
    redis 127.0.0.1:6379> SET runoob "菜鸟教程"
    OK # SET 命令
    redis 127.0.0.1:6379> GET runoob
    "菜鸟教程" # GET 命令
    ```

- Hash (哈希)

  - 一个键值 (key=>value) 对集合，每个 hash 可存储 2^32 - 1 个键值对

  - 一个 string 类型的 field (属性) 和 value 的映射表，时和用于存储对象

  - ```shell
    redis 127.0.0.1:6379> DEL runoob # 要先删除用过的不同类型 key
    redis 127.0.0.1:6379> HMSET runoob field1 "Hello" field2 "World"
    "OK" # 使用 HMSET 命令
    redis 127.0.0.1:6379> HGET runoob field1
    "Hello" # 使用 HGET 命令，指定 key 和其 field
    redis 127.0.0.1:6379> HGET runoob field2
    "World"
    ```

- List (列表)

  - 是简单的字符串列表，按照插入顺序排序。可以在首、尾添加

  - ```shell
    redis 127.0.0.1:6379> lpush runoob redis
    (integer) 1
    redis 127.0.0.1:6379> lpush runoob mongodb
    (integer) 2
    redis 127.0.0.1:6379> lpush runoob rabbitmq
    (integer) 3
    redis 127.0.0.1:6379> lrange runoob 0 10
    1) "rabbitmq"
    2) "mongodb"
    3) "redis"
    ```

- Set (集合)

  - 是 string 类型的无序集合，通过哈希实现，增删查都是 O(1)，容量同上

  - ```shell
    sadd key member # 添加成功返回 1，元素已存在则返回 0
    ```

- zset (sorted set: 有序集合)

  - 上面基础上每个元素关联一个 double 类型的分数实现从小到大排序

  - 成员唯一，但分数(score)可以重复

  - ```shell
    zadd key score member # 添加成功返回 1，已存在返回 0
    ```

补充：有关 zset 的复杂度——当 items 内容大于 64 时使用了 hash 和 skiplist 两种设计实现，而增删都需要修改 skiplist 故复杂度为 O(log(n))，而查找仅用 hash ，复杂度 O(1)；

当 items 小于 64 时则采用 ziplist 设计，时间复杂度为 O(n)

## Redis 命令

用于在 redis 服务上执行操作。需要一个 redis 客户端

各命令小写也可以，统一就行

#### 本地

```shell
$ redis-cli
> PING
PONG # 返回
```

#### 远程

```shell
$ redis-cli -h host -p port -a password
$redis-cli -h 127.0.0.1 -p 6379 -a "mypass" # 例子，主机、端口、密码
```

### 键 （key）命令

```shell
> COMMAND key_name
```

| 序号 | [命令及描述](https://redis.io/commands)                      |
| :--- | :----------------------------------------------------------- |
| 1    | [DEL key](https://www.runoob.com/redis/keys-del.html) 该命令用于在 key 存在时删除 key。 |
| 2    | [DUMP key](https://www.runoob.com/redis/keys-dump.html) 序列化给定 key ，并返回被序列化的值。 |
| 3    | [EXISTS key](https://www.runoob.com/redis/keys-exists.html) 检查给定 key 是否存在。返回 1 或 0 |
| 4    | [EXPIRE key](https://www.runoob.com/redis/keys-expire.html) seconds 为给定 key 设置过期时间，以秒计。 |
| 5    | [EXPIREAT key timestamp](https://www.runoob.com/redis/keys-expireat.html) EXPIREAT 的作用和 EXPIRE 类似，都用于为 key 设置过期时间。 不同在于 EXPIREAT 命令接受的时间参数是 UNIX 时间戳(unix timestamp)。 |
| 6    | [PEXPIRE key milliseconds](https://www.runoob.com/redis/keys-pexpire.html) 设置 key 的过期时间以毫秒计。 |
| 7    | [PEXPIREAT key milliseconds-timestamp](https://www.runoob.com/redis/keys-pexpireat.html) 设置 key 过期时间的时间戳(unix timestamp) 以毫秒计 |
| 8    | [KEYS pattern](https://www.runoob.com/redis/keys-keys.html) 查找所有符合给定模式( pattern)的 key 。传入正则模式 |
| 9    | [MOVE key db](https://www.runoob.com/redis/keys-move.html) 将当前数据库的 key 移动到给定的数据库 db 当中。 |
| 10   | [PERSIST key](https://www.runoob.com/redis/keys-persist.html) 移除 key 的过期时间，key 将持久保持。 |
| 11   | [PTTL key](https://www.runoob.com/redis/keys-pttl.html) 以毫秒为单位返回 key 的剩余的过期时间。 |
| 12   | [TTL key](https://www.runoob.com/redis/keys-ttl.html) 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。 |
| 13   | [RANDOMKEY](https://www.runoob.com/redis/keys-randomkey.html) 从当前数据库中随机返回一个 key 。 |
| 14   | [RENAME key newkey](https://www.runoob.com/redis/keys-rename.html) 修改 key 的名称 |
| 15   | [RENAMENX key newkey](https://www.runoob.com/redis/keys-renamenx.html) 仅当 newkey 不存在时，将 key 改名为 newkey 。 |
| 16   | [SCAN cursor [MATCH pattern\] [COUNT count]](https://www.runoob.com/redis/keys-scan.html) 迭代数据库中的数据库键。 |
| 17   | [TYPE key](https://www.runoob.com/redis/keys-type.html) 返回 key 所储存的值的类型。 |

### 字符串命令

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [SET key value](https://www.runoob.com/redis/strings-set.html) 设置指定 key 的值 |
| 2    | [GET key](https://www.runoob.com/redis/strings-get.html) 获取指定 key 的值。 |
| 3    | [GETRANGE key start end](https://www.runoob.com/redis/strings-getrange.html) 返回 key 中字符串值的子字符 |
| 4    | [GETSET key value](https://www.runoob.com/redis/strings-getset.html) 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。 |
| 5    | [GETBIT key offset](https://www.runoob.com/redis/strings-getbit.html) 对 key 所储存的字符串值，获取指定偏移量上的位(bit)。 |
| 6    | [MGET key1 \[key2..]](https://www.runoob.com/redis/strings-mget.html) 获取所有(一个或多个)给定 key 的值。 |
| 7    | [SETBIT key offset value](https://www.runoob.com/redis/strings-setbit.html) 对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)。 |
| 8    | [SETEX key seconds value](https://www.runoob.com/redis/strings-setex.html) 将值 value 关联到 key ，并将 key 的过期时间设为 seconds (以秒为单位)。 |
| 9    | [SETNX key value](https://www.runoob.com/redis/strings-setnx.html) 只有在 key 不存在时设置 key 的值。 |
| 10   | [SETRANGE key offset value](https://www.runoob.com/redis/strings-setrange.html) 用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始。 |
| 11   | [STRLEN key](https://www.runoob.com/redis/strings-strlen.html) 返回 key 所储存的字符串值的长度。 |
| 12   | [MSET key value \[key value ...]](https://www.runoob.com/redis/strings-mset.html) 同时设置一个或多个 key-value 对。 |
| 13   | [MSETNX key value \[key value ...]](https://www.runoob.com/redis/strings-msetnx.html) 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在。 |
| 14   | [PSETEX key milliseconds value](https://www.runoob.com/redis/strings-psetex.html) 这个命令和 SETEX 命令相似，但它以毫秒为单位设置 key 的生存时间，而不是像 SETEX 命令那样，以秒为单位。 |
| 15   | [INCR key](https://www.runoob.com/redis/strings-incr.html) 将 key 中储存的数字值增一。 |
| 16   | [INCRBY key increment](https://www.runoob.com/redis/strings-incrby.html) 将 key 所储存的值加上给定的增量值（increment） 。 |
| 17   | [INCRBYFLOAT key increment](https://www.runoob.com/redis/strings-incrbyfloat.html) 将 key 所储存的值加上给定的浮点增量值（increment） 。 |
| 18   | [DECR key](https://www.runoob.com/redis/strings-decr.html) 将 key 中储存的数字值减一。 |
| 19   | [DECRBY key decrement](https://www.runoob.com/redis/strings-decrby.html) key 所储存的值减去给定的减量值（decrement） 。 |
| 20   | [APPEND key value](https://www.runoob.com/redis/strings-append.html) 如果 key 已经存在并且是一个字符串， APPEND 命令将指定的 value 追加到该 key 原来值（value）的末尾。 |

### 哈希命令

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [HDEL key field1 \[field2]](https://www.runoob.com/redis/hashes-hdel.html) 删除一个或多个哈希表字段 |
| 2    | [HEXISTS key field](https://www.runoob.com/redis/hashes-hexists.html) 查看哈希表 key 中，指定的字段是否存在。 |
| 3    | [HGET key field](https://www.runoob.com/redis/hashes-hget.html) 获取存储在哈希表中指定字段的值。 |
| 4    | [HGETALL key](https://www.runoob.com/redis/hashes-hgetall.html) 获取在哈希表中指定 key 的所有字段和值 |
| 5    | [HINCRBY key field increment](https://www.runoob.com/redis/hashes-hincrby.html) 为哈希表 key 中的指定字段的整数值加上增量 increment 。 |
| 6    | [HINCRBYFLOAT key field increment](https://www.runoob.com/redis/hashes-hincrbyfloat.html) 为哈希表 key 中的指定字段的浮点数值加上增量 increment 。 |
| 7    | [HKEYS key](https://www.runoob.com/redis/hashes-hkeys.html) 获取所有哈希表中的字段 |
| 8    | [HLEN key](https://www.runoob.com/redis/hashes-hlen.html) 获取哈希表中字段的数量 |
| 9    | [HMGET key field1 \[field2]](https://www.runoob.com/redis/hashes-hmget.html) 获取所有给定字段的值 |
| 10   | [HMSET key field1 value1 \[field2 value2 ]](https://www.runoob.com/redis/hashes-hmset.html) 同时将多个 field-value (域-值)对设置到哈希表 key 中。 |
| 11   | [HSET key field value](https://www.runoob.com/redis/hashes-hset.html) 将哈希表 key 中的字段 field 的值设为 value 。 |
| 12   | [HSETNX key field value](https://www.runoob.com/redis/hashes-hsetnx.html) 只有在字段 field 不存在时，设置哈希表字段的值。 |
| 13   | [HVALS key](https://www.runoob.com/redis/hashes-hvals.html) 获取哈希表中所有值。 |
| 14   | [HSCAN key cursor [MATCH pattern\] [COUNT count]](https://www.runoob.com/redis/hashes-hscan.html) 迭代哈希表中的键值对。 |

### 列表命令

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [BLPOP key1 \[key2 ] timeout](https://www.runoob.com/redis/lists-blpop.html) 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。 |
| 2    | [BRPOP key1 \[key2 ] timeout](https://www.runoob.com/redis/lists-brpop.html) 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。 |
| 3    | [BRPOPLPUSH source destination timeout](https://www.runoob.com/redis/lists-brpoplpush.html) 从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。 |
| 4    | [LINDEX key index](https://www.runoob.com/redis/lists-lindex.html) 通过索引获取列表中的元素，索引以 0 开始 |
| 5    | [LINSERT key BEFORE\|AFTER pivot value](https://www.runoob.com/redis/lists-linsert.html) 在列表的元素前或者后插入元素 |
| 6    | [LLEN key](https://www.runoob.com/redis/lists-llen.html) 获取列表长度 |
| 7    | [LPOP key](https://www.runoob.com/redis/lists-lpop.html) 移出并获取列表的第一个元素 |
| 8    | [LPUSH key value1 \[value2]](https://www.runoob.com/redis/lists-lpush.html) 将一个或多个值插入到列表头部 |
| 9    | [LPUSHX key value](https://www.runoob.com/redis/lists-lpushx.html) 将一个值插入到已存在的列表头部 |
| 10   | [LRANGE key start stop](https://www.runoob.com/redis/lists-lrange.html) 获取列表指定范围内的元素 |
| 11   | [LREM key count value](https://www.runoob.com/redis/lists-lrem.html) 移除列表元素 |
| 12   | [LSET key index value](https://www.runoob.com/redis/lists-lset.html) 通过索引设置列表元素的值 |
| 13   | [LTRIM key start stop](https://www.runoob.com/redis/lists-ltrim.html) 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。 |
| 14   | [RPOP key](https://www.runoob.com/redis/lists-rpop.html) 移除列表的最后一个元素，返回值为移除的元素。 |
| 15   | [RPOPLPUSH source destination](https://www.runoob.com/redis/lists-rpoplpush.html) 移除列表的最后一个元素，并将该元素添加到另一个列表并返回 |
| 16   | [RPUSH key value1 \[value2]](https://www.runoob.com/redis/lists-rpush.html) 在列表中添加一个或多个值 |
| 17   | [RPUSHX key value](https://www.runoob.com/redis/lists-rpushx.html) 为已存在的列表添加值 |

### 集合命令

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [SADD key member1 \[member2]](https://www.runoob.com/redis/sets-sadd.html) 向集合添加一个或多个成员 |
| 2    | [SCARD key](https://www.runoob.com/redis/sets-scard.html) 获取集合的成员数 |
| 3    | [SDIFF key1 \[key2]](https://www.runoob.com/redis/sets-sdiff.html) 返回第一个集合与其他集合之间的差异。 |
| 4    | [SDIFFSTORE destination key1 \[key2]](https://www.runoob.com/redis/sets-sdiffstore.html) 返回给定所有集合的差集并存储在 destination 中 |
| 5    | [SINTER key1 \[key2]](https://www.runoob.com/redis/sets-sinter.html) 返回给定所有集合的交集 |
| 6    | [SINTERSTORE destination key1 \[key2]](https://www.runoob.com/redis/sets-sinterstore.html) 返回给定所有集合的交集并存储在 destination 中 |
| 7    | [SISMEMBER key member](https://www.runoob.com/redis/sets-sismember.html) 判断 member 元素是否是集合 key 的成员 |
| 8    | [SMEMBERS key](https://www.runoob.com/redis/sets-smembers.html) 返回集合中的所有成员 |
| 9    | [SMOVE source destination member](https://www.runoob.com/redis/sets-smove.html) 将 member 元素从 source 集合移动到 destination 集合 |
| 10   | [SPOP key](https://www.runoob.com/redis/sets-spop.html) 移除并返回集合中的一个随机元素 |
| 11   | [SRANDMEMBER key \[count]](https://www.runoob.com/redis/sets-srandmember.html) 返回集合中一个或多个随机数 |
| 12   | [SREM key member1 \[member2]](https://www.runoob.com/redis/sets-srem.html) 移除集合中一个或多个成员 |
| 13   | [SUNION key1 \[key2]](https://www.runoob.com/redis/sets-sunion.html) 返回所有给定集合的并集 |
| 14   | [SUNIONSTORE destination key1 \[key2]](https://www.runoob.com/redis/sets-sunionstore.html) 所有给定集合的并集存储在 destination 集合中 |
| 15   | [SSCAN key cursor [MATCH pattern\] [COUNT count]](https://www.runoob.com/redis/sets-sscan.html) 迭代集合中的元素 |

### 有序集合

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [ZADD key score1 member1 \[score2 member2]](https://www.runoob.com/redis/sorted-sets-zadd.html) 向有序集合添加一个或多个成员，或者更新已存在成员的分数 |
| 2    | [ZCARD key](https://www.runoob.com/redis/sorted-sets-zcard.html) 获取有序集合的成员数 |
| 3    | [ZCOUNT key min max](https://www.runoob.com/redis/sorted-sets-zcount.html) 计算在有序集合中指定区间分数的成员数 |
| 4    | [ZINCRBY key increment member](https://www.runoob.com/redis/sorted-sets-zincrby.html) 有序集合中对指定成员的分数加上增量 increment |
| 5    | [ZINTERSTORE destination numkeys key \[key ...]](https://www.runoob.com/redis/sorted-sets-zinterstore.html) 计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 destination 中 |
| 6    | [ZLEXCOUNT key min max](https://www.runoob.com/redis/sorted-sets-zlexcount.html) 在有序集合中计算指定字典区间内成员数量 |
| 7    | [ZRANGE key start stop \[WITHSCORES]](https://www.runoob.com/redis/sorted-sets-zrange.html) 通过索引区间返回有序集合指定区间内的成员 |
| 8    | [ZRANGEBYLEX key min max \[LIMIT offset count]](https://www.runoob.com/redis/sorted-sets-zrangebylex.html) 通过字典区间返回有序集合的成员 |
| 9    | [ZRANGEBYSCORE key min max [WITHSCORES\] [LIMIT]](https://www.runoob.com/redis/sorted-sets-zrangebyscore.html) 通过分数返回有序集合指定区间内的成员 |
| 10   | [ZRANK key member](https://www.runoob.com/redis/sorted-sets-zrank.html) 返回有序集合中指定成员的索引 |
| 11   | [ZREM key member \[member ...]](https://www.runoob.com/redis/sorted-sets-zrem.html) 移除有序集合中的一个或多个成员 |
| 12   | [ZREMRANGEBYLEX key min max](https://www.runoob.com/redis/sorted-sets-zremrangebylex.html) 移除有序集合中给定的字典区间的所有成员 |
| 13   | [ZREMRANGEBYRANK key start stop](https://www.runoob.com/redis/sorted-sets-zremrangebyrank.html) 移除有序集合中给定的排名区间的所有成员 |
| 14   | [ZREMRANGEBYSCORE key min max](https://www.runoob.com/redis/sorted-sets-zremrangebyscore.html) 移除有序集合中给定的分数区间的所有成员 |
| 15   | [ZREVRANGE key start stop \[WITHSCORES]](https://www.runoob.com/redis/sorted-sets-zrevrange.html) 返回有序集中指定区间内的成员，通过索引，分数从高到低 |
| 16   | [ZREVRANGEBYSCORE key max min \[WITHSCORES]](https://www.runoob.com/redis/sorted-sets-zrevrangebyscore.html) 返回有序集中指定分数区间内的成员，分数从高到低排序 |
| 17   | [ZREVRANK key member](https://www.runoob.com/redis/sorted-sets-zrevrank.html) 返回有序集合中指定成员的排名，有序集成员按分数值递减(从大到小)排序 |
| 18   | [ZSCORE key member](https://www.runoob.com/redis/sorted-sets-zscore.html) 返回有序集中，成员的分数值 |
| 19   | [ZUNIONSTORE destination numkeys key \[key ...]](https://www.runoob.com/redis/sorted-sets-zunionstore.html) 计算给定的一个或多个有序集的并集，并存储在新的 key 中 |
| 20   | [ZSCAN key cursor [MATCH pattern\] [COUNT count]](https://www.runoob.com/redis/sorted-sets-zscan.html) 迭代有序集合中的元素（包括元素成员和元素分值） |

### HyperLogLog 结构

从 2.8.9 版本新增，仅在输入元素时统计基数，而不存储元素本身，迅速返回元素个数

```shell
redis 127.0.0.1:6379> PFADD runoobkey "redis"
1) (integer) 1
redis 127.0.0.1:6379> PFADD runoobkey "mongodb"
1) (integer) 1
redis 127.0.0.1:6379> PFADD runoobkey "mysql"
1) (integer) 1
redis 127.0.0.1:6379> PFCOUNT runoobkey
(integer) 3
```

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [PFADD key element \[element ...]](https://www.runoob.com/redis/hyperloglog-pfadd.html) 添加指定元素到 HyperLogLog 中。 |
| 2    | [PFCOUNT key \[key ...]](https://www.runoob.com/redis/hyperloglog-pfcount.html) 返回给定 HyperLogLog 的基数估算值。 |
| 3    | [PFMERGE destkey sourcekey \[sourcekey ...]](https://www.runoob.com/redis/hyperloglog-pfmerge.html) 将多个 HyperLogLog 合并为一个 HyperLogLog |

### 发布订阅

pub/sub 是一种消息通信模式：发送者(pub)发送消息，订阅者(sub)接收消息

Redis 客户端可以订阅任意数量的频道

1. 开启第一个客户端，执行 `SUBSCRIBE runoobChat` 创建订阅频道 **runoobChat**
2. 开启其他客户端，执行 `PUBLISH runoobChat message`向频道发送消息
3. 能看到第一个客户端接收到的消息

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [PSUBSCRIBE pattern \[pattern ...]](https://www.runoob.com/redis/pub-sub-psubscribe.html) 订阅一个或多个符合给定模式的频道。 |
| 2    | [PUBSUB subcommand [argument [argument ...\]]](https://www.runoob.com/redis/pub-sub-pubsub.html) 查看订阅与发布系统状态。 |
| 3    | [PUBLISH channel message](https://www.runoob.com/redis/pub-sub-publish.html) 将信息发送到指定的频道。 |
| 4    | [PUNSUBSCRIBE [pattern [pattern ...\]]](https://www.runoob.com/redis/pub-sub-punsubscribe.html) 退订所有给定模式的频道。 |
| 5    | [SUBSCRIBE channel \[channel ...]](https://www.runoob.com/redis/pub-sub-subscribe.html) 订阅给定的一个或多个频道的信息。 |
| 6    | [UNSUBSCRIBE [channel [channel ...\]]](https://www.runoob.com/redis/pub-sub-unsubscribe.html) 指退订给定的频道。 |

只能直接退出客户端不能仅退出聊天频道吗？

### 事务

可以一次执行多个命令，并且带有以下三个重要的保证：

1. 批量操作在发送 EXEC 命令前被放入队列缓存
2. 收到 EXEC 命令后进入事务执行，事务中任意命令执行失败，其余的命令依然被执行
3. 在事务执行过程，其他客户端提交的命令请求不会插入到事务执行命令序列中

开始到执行的三个阶段：开始事务、命令入队、执行事务。

单个命令是原子性的，但事务不是：遇到失败不会回滚，后面也会继续执行

```shell
redis 127.0.0.1:7000> multi # 开始一个事务
OK
redis 127.0.0.1:7000> set a aaa # 将多个命令入队到事务中
QUEUED
redis 127.0.0.1:7000> set b bbb
QUEUED
redis 127.0.0.1:7000> set c ccc
QUEUED
redis 127.0.0.1:7000> exec # 触发事务，执行其中所有命令
1) OK
2) OK
3) OK
```

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [DISCARD](https://www.runoob.com/redis/transactions-discard.html) 取消事务，放弃执行事务块内的所有命令。 |
| 2    | [EXEC](https://www.runoob.com/redis/transactions-exec.html) 执行所有事务块内的命令。 |
| 3    | [MULTI](https://www.runoob.com/redis/transactions-multi.html) 标记一个事务块的开始。 |
| 4    | [UNWATCH](https://www.runoob.com/redis/transactions-unwatch.html) 取消 WATCH 命令对所有 key 的监视。 |
| 5    | [WATCH key \[key ...]](https://www.runoob.com/redis/transactions-watch.html) 监视一个(或多个) key ，如果在事务执行之前这个(或这些) key 被其他命令所改动，那么事务将被打断。 |

### Redis 脚本

使用 Lua 解释器来执行脚本。从 2.6 开始内嵌了。常用命令 `EVAL`

```shell
redis 127.0.0.1:6379> EVAL "return {KEYS[1],KEYS[2],ARGV[1],ARGV[2]}" 2 key1 key2 first second
# 直接写在内部的简单脚本
1) "key1"
2) "key2"
3) "first"
4) "second"
```

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [EVAL script numkeys key [key ...\] arg [arg ...]](https://www.runoob.com/redis/scripting-eval.html) 执行 Lua 脚本。 |
| 2    | [EVALSHA sha1 numkeys key [key ...\] arg [arg ...]](https://www.runoob.com/redis/scripting-evalsha.html) 执行 Lua 脚本。 |
| 3    | [SCRIPT EXISTS script [script ...\]](https://www.runoob.com/redis/scripting-script-exists.html) 查看指定的脚本是否已经被保存在缓存当中。 |
| 4    | [SCRIPT FLUSH](https://www.runoob.com/redis/scripting-script-flush.html) 从脚本缓存中移除所有脚本。 |
| 5    | [SCRIPT KILL](https://www.runoob.com/redis/scripting-script-kill.html) 杀死当前正在运行的 Lua 脚本。 |
| 6    | [SCRIPT LOAD script](https://www.runoob.com/redis/scripting-script-load.html) 将脚本 script 添加到脚本缓存中，但并不立即执行这个脚本。 |

### Redis 连接命令

```shell
redis 127.0.0.1:6379> AUTH "password"
OK # 连接 redis 服务
```

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [AUTH password](https://www.runoob.com/redis/connection-auth.html) 验证密码是否正确 |
| 2    | [ECHO message](https://www.runoob.com/redis/connection-echo.html) 打印字符串 |
| 3    | [PING](https://www.runoob.com/redis/connection-ping.html) 查看服务是否运行 |
| 4    | [QUIT](https://www.runoob.com/redis/connection-quit.html) 关闭当前连接 |
| 5    | [SELECT index](https://www.runoob.com/redis/connection-select.html) 切换到指定的数据库 |

### Redis 服务器命令

用于管理 redis 服务

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [BGREWRITEAOF](https://www.runoob.com/redis/server-bgrewriteaof.html) 异步执行一个 AOF（AppendOnly File） 文件重写操作 |
| 2    | [BGSAVE](https://www.runoob.com/redis/server-bgsave.html) 在后台异步保存当前数据库的数据到磁盘 |
| 3    | [CLIENT KILL [ip:port\] [ID client-id]](https://www.runoob.com/redis/server-client-kill.html) 关闭客户端连接 |
| 4    | [CLIENT LIST](https://www.runoob.com/redis/server-client-list.html) 获取连接到服务器的客户端连接列表 |
| 5    | [CLIENT GETNAME](https://www.runoob.com/redis/server-client-getname.html) 获取连接的名称 |
| 6    | [CLIENT PAUSE timeout](https://www.runoob.com/redis/server-client-pause.html) 在指定时间内终止运行来自客户端的命令 |
| 7    | [CLIENT SETNAME connection-name](https://www.runoob.com/redis/server-client-setname.html) 设置当前连接的名称 |
| 8    | [CLUSTER SLOTS](https://www.runoob.com/redis/server-cluster-slots.html) 获取集群节点的映射数组 |
| 9    | [COMMAND](https://www.runoob.com/redis/server-command.html) 获取 Redis 命令详情数组 |
| 10   | [COMMAND COUNT](https://www.runoob.com/redis/server-command-count.html) 获取 Redis 命令总数 |
| 11   | [COMMAND GETKEYS](https://www.runoob.com/redis/server-command-getkeys.html) 获取给定命令的所有键 |
| 12   | [TIME](https://www.runoob.com/redis/server-time.html) 返回当前服务器时间 |
| 13   | [COMMAND INFO command-name [command-name ...\]](https://www.runoob.com/redis/server-command-info.html) 获取指定 Redis 命令描述的数组 |
| 14   | [CONFIG GET parameter](https://www.runoob.com/redis/server-config-get.html) 获取指定配置参数的值 |
| 15   | [CONFIG REWRITE](https://www.runoob.com/redis/server-config-rewrite.html) 对启动 Redis 服务器时所指定的 redis.conf 配置文件进行改写 |
| 16   | [CONFIG SET parameter value](https://www.runoob.com/redis/server-config-set.html) 修改 redis 配置参数，无需重启 |
| 17   | [CONFIG RESETSTAT](https://www.runoob.com/redis/server-config-resetstat.html) 重置 INFO 命令中的某些统计数据 |
| 18   | [DBSIZE](https://www.runoob.com/redis/server-dbsize.html) 返回当前数据库的 key 的数量 |
| 19   | [DEBUG OBJECT key](https://www.runoob.com/redis/server-debug-object.html) 获取 key 的调试信息 |
| 20   | [DEBUG SEGFAULT](https://www.runoob.com/redis/server-debug-segfault.html) 让 Redis 服务崩溃 |
| 21   | [FLUSHALL](https://www.runoob.com/redis/server-flushall.html) 删除所有数据库的所有key |
| 22   | [FLUSHDB](https://www.runoob.com/redis/server-flushdb.html) 删除当前数据库的所有key |
| 23   | [INFO \[section]](https://www.runoob.com/redis/server-info.html) 获取 Redis 服务器的各种信息和统计数值，传入如 Server、Client等 |
| 24   | [LASTSAVE](https://www.runoob.com/redis/server-lastsave.html) 返回最近一次 Redis 成功将数据保存到磁盘上的时间，以 UNIX 时间戳格式表示 |
| 25   | [MONITOR](https://www.runoob.com/redis/server-monitor.html) 实时打印出 Redis 服务器接收到的命令，调试用 |
| 26   | [ROLE](https://www.runoob.com/redis/server-role.html) 返回主从实例所属的角色 |
| 27   | [SAVE](https://www.runoob.com/redis/server-save.html) 同步保存数据到硬盘 |
| 28   | [SHUTDOWN [NOSAVE\] [SAVE]](https://www.runoob.com/redis/server-shutdown.html) 异步保存数据到硬盘，并关闭服务器 |
| 29   | [SLAVEOF host port](https://www.runoob.com/redis/server-slaveof.html) 将当前服务器转变为指定服务器的从属服务器(slave server) |
| 30   | [SLOWLOG subcommand \[argument]](https://www.runoob.com/redis/server-showlog.html) 管理 redis 的慢日志 |
| 31   | [SYNC](https://www.runoob.com/redis/server-sync.html) 用于复制功能(replication)的内部命令 |

### [Redis GEO](https://www.runoob.com/redis/redis-geo.html)

主要用于存储地理位置信息，并对存储的信息进行操作，于 3.2 新增

操作方法有：

- geoadd：添加地理位置的坐标
- geopos：获取地理位置的坐标
- geodist：计算两个位置之间的距离
- georadius：根据用户给定的经纬度坐标来获取指定范围内的地理位置集合
- georadiusbymember：根据储存在位置集合里面的某个地点获取指定范围内的地理位置集合
- geohash：返回一个或多个位置对象的 geohash 值

### [Redis Stream](https://www.runoob.com/redis/redis-stream.html)

是 5.0 版本新增数据结构

主要用于消息队列（MQ, Message Queue），原来的发布订阅缺点，消息无法持久化，如果出现网络断开、Redis 宕机等，消息就会被丢弃。

Redis Stream 提供了消息的持久化和主备赋值功能，可以让任何客户端访问任何时刻的数据，并且能记住每一个客户端的访问位置，还能保证消息不丢失。

## Advanced

### 数据备份与恢复

`SAVE` 命令用于创建当前数据库的备份，将在安装目录下创建 dump.rdb 文件

将备份文件移动到安装目录即可恢复，由以下指令获取目录

```shell
CONFIG GET dir
```

`BGSAVE` 在后台执行备份

### 安全

可以通过**配置文件**设置密码参数，当客户端连接服务时需要密码验证

```shell
CONFIG get requirepass # 查看是否设置了密码验证，为空表示未设密码
```

### 性能测试

通过同时执行多个命令实现

```shell
redis-benchmark [option] [option value]
$ redis-benchmark -n 10000  -q # 同时执行 10000 个请求来检测性能
$ redis-benchmark -h 127.0.0.1 -p 6379 -t set,lpush -n 10000 -q
```

可选参数

|      |                           |                                            |           |
| :--- | :------------------------ | :----------------------------------------- | :-------- |
| 序号 | 选项                      | 描述                                       | 默认值    |
| 1    | **-h**                    | 指定服务器主机名                           | 127.0.0.1 |
| 2    | **-p**                    | 指定服务器端口                             | 6379      |
| 3    | **-s**                    | 指定服务器 socket                          |           |
| 4    | **-c**                    | 指定并发连接数                             | 50        |
| 5    | **-n**                    | 指定请求数                                 | 10000     |
| 6    | **-d**                    | 以字节的形式指定 SET/GET 值的数据大小      | 2         |
| 7    | **-k**                    | 1=keep alive 0=reconnect                   | 1         |
| 8    | **-r**                    | SET/GET/INCR 使用随机 key, SADD 使用随机值 |           |
| 9    | **-P**                    | 通过管道传输 <numreq> 请求                 | 1         |
| 10   | **-q**                    | 强制退出 redis。仅显示 query/sec 值        |           |
| 11   | **--csv**                 | 以 CSV 格式输出                            |           |
| 12   | ***-l\*（L 的小写字母）** | 生成循环，永久执行测试                     |           |
| 13   | **-t**                    | 仅运行以逗号分隔的测试命令列表。           |           |
| 14   | ***-I\*（i 的大写字母）** | Idle 模式。仅打开 N 个 idle 连接并等待。   |           |

### 客户端连接

通过监听一个 TCP 端口或者 Unix Socket 的方式来接收来自客户端的连接，当一个连接建立后，Redis 内部会进行以下一些操作：

- 首先，客户端 socket 会被设置为非阻塞模式，因为 Redis 在网络事件处理上采用的时非阻塞多路复用模型
- 然后为这个 socket 设置 TCP_NODELAY 属性，禁用 Nagle 算法
- 然后创建一个可读的文件事件用于监听这个客户端 socket 的数据发送

客户端命令

| S.N. | 命令               | 描述                                       |
| :--- | :----------------- | :----------------------------------------- |
| 1    | **CLIENT LIST**    | 返回连接到 redis 服务的客户端列表          |
| 2    | **CLIENT SETNAME** | 设置当前连接的名称                         |
| 3    | **CLIENT GETNAME** | 获取通过 CLIENT SETNAME 命令设置的服务名称 |
| 4    | **CLIENT PAUSE**   | 挂起客户端连接，指定挂起的时间以毫秒计     |
| 5    | **CLIENT KILL**    | 关闭客户端连接                             |

### [管道技术](https://www.runoob.com/redis/redis-pipelining.html)

Redis是一种基于客户端-服务端模型以及请求/响应协议的TCP服务。这意味着通常情况下一个请求会遵循以下步骤：

- 客户端向服务端发送一个查询请求，并监听Socket返回，通常是以阻塞模式，等待服务端响应。
- 服务端处理命令，并将结果返回给客户端。

管道技术可以在服务端未响应时，客户端可以继续向服务端发送请求，并最终一次性读取所有服务端的响应。

### Redis 分区

分割数据到多个 Redis 实例的处理过程，每个实例只保存 key 的一个子集

