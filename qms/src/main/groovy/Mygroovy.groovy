class Mygroovy {


    public static void main(String[] args) {
//        def citys = [:]
//        citys << ['全国': 'www']
//        citys['dd'] = "name"
//        citys.each {
//            println it.key + ":" + it.value
//        }

//        def list=[]
//        list<<1
//        list<<2
//        list<<3
//        def findlist=list.findAll{
//            item->item>1
//        }
     //        println(findlist.join(","))


//       def a = [1, 2, 3,8]
//      def b = [4, 5, 6,8]
////        def m = [:]
////        [a, b].transpose().each { k, v -> m += [(k): v] }
////        assert m != [1: 4, 2: 5, 3: 6]:"不相等"
//        def list= [a,b].transpose()
//    println(list)
      //  def m3 = [a, b].transpose().inject([:]) { s, l -> s + [(l[0]): l[1]] }
        //assert m3 == [1: 4, 2: 5, 3: 6]
        [[1,2],[6,7]].each { k, v ->
            println "k:${k},v:${v}"

        }




    }
}